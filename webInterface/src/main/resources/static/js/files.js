$(function () {
    $("a[id^='delFile']").click(function () {
        event.preventDefault();
        if (confirm($(this).attr('data-confirm-text'))) {
            let fileId = $(this).attr('name').replace('delFile_', '');
            $.ajax({
                url: 'file/' + fileId,
                type: 'DELETE',
                success: function (result) {
                    location.reload();
                }
            });
        }
    });

    $("#pageSizeField").blur(function () {
        let value = $(this).val();
        if (!value || !($.isNumeric(value) && value > 0)) {
            $(this).css("border-color", "red");
        } else {
            $(this).css("border", "");
            $("#pageSizeForm").submit();
        }
    });

    $("#fileInputButton").change(function () {
        $("#fileInputForm").submit();
    });
});
