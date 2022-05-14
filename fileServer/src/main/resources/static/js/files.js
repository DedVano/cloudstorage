$(function () {
    fillData(null, 0, 3);
    $('#createNew').click(function () {
        $.ajax({
            url: '/api/files',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: JSON.stringify({
                'name': 'newfilefromsite',
                'sizeKb': 443
            }),
            success: function (result) {
                alert(result);
            }
        });
    });
    $('#edit').click(function () {
        $.ajax({
            url: '/api/files',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'PUT',
            data: JSON.stringify({
                'id': 6,
                'name': 'alsoanotherfile_edited.txt',
                'sizeKb': 175
            }),
            success: function (result) {
                alert(result.name, result.sizeKb);
            }
        });
    });
    $('#delete').click(function () {
        $.ajax({
            url: 'api/files/6',
            type: 'DELETE'
        });
    });
});



function fillData(owner, page, size) {
    $.get('/api/files' + ((owner || page || size) ? '?' : '') + (owner ? 'owner=' + owner : '') +
        (((owner && page) || (owner && size)) ? '&' : '') + (page ? 'page=' + page : '') +
        ((page && size) ? '&' : '') + (size ? 'size=' + size : ''), function (result) {
        let rowsData = '';
        for (let i = 0; i < result.fileEntityList.length; i++) {
            let file = result.fileEntityList[i];
            var actionButton = '';
            // if (result.admin) {
                actionButton =
                    '<td>'
                    + '<a href="/api/files/' + file.id + '" download=""><img title="' + $('#localizedMessages').attr('data-downloadItem')
                    + '" src="/images/download.png"/></a>'
                    + '<a href="/genre/edit?code=' + file.code + '"><img title="Edit" src="/images/edit.png"/></a>'
                    + '<a data-id="genre_' + file.code + '" href="genre.html"><img title="Delete" src="/images/delete.png"/></a>'
                    + '</td>'
            // }

            var fileDate = new Date(Date.parse(file.creationDateTime))

            rowsData += '<tr><td>' + file.id + '</td>'
                + '<td>' + file.name + '</td>'
                + '<td>' + file.sizeKb + '</td>'
                + '<td>' + fileDate.getFullYear() + '/' + fileDate.getMonth() + '/' + fileDate.getDate()
                + ' ' + fileDate.getHours() + ':' + fileDate.getMinutes() + ':' + fileDate.getSeconds() + '</td>'
                // + '<td>' + file.ownerId + '</td>'
                + actionButton
                + '</tr>';
        }
        $('#filesList').html(rowsData);
        // if (result.admin) {
        //     $('a[data-id^=genre_]').one('click', function (event) {
        //         if (confirm('Are you sure to delete this genre?')) {
        //             let genreCode = $(this).attr('data-id').replace('genre_', '');
        //             $.ajax({
        //                 url: '/api/genres/' + genreCode,
        //                 type: 'DELETE',
        //                 success: function () {
        //                     location.href = '/genres';
        //                 },
        //                 error: function (jqXHR, exception) {
        //                     if (jqXHR.status === 403) {
        //                         alert('You have no right');
        //                     }
        //                 }
        //             });
        //         }
        //         event.preventDefault();
        //     });
        // }

        $('#navigation').html(
            (result.hasPreviousPage ? ('<a href="javascript:fillData(' + owner + ', ' + (result.currentPage - 1)+ ', ' + size +')">'
                + '<img title="' + $('#localizedMessages').attr('data-prevPage') + '" src="/images/previous.png"/></a>') : '')
            + $('#localizedMessages').attr('data-currentPage') + '<span> ' + (result.currentPage + 1) + ' </span>'
            + $('#localizedMessages').attr('data-pageOf') + '<span> ' + result.totalPages + '</span>'
            + (result.hasNextPage ?  ('<a href="javascript:fillData(' + owner + ', ' + (result.currentPage + 1)+ ', ' + size +')">'
                + '<img title="' + $('#localizedMessages').attr('data-nextPage') + '" src="/images/next.png"/></a>') : ''));
    });
}