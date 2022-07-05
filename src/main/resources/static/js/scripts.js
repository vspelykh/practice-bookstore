$(document).ready(function() {

    var table = $('#bookTable').dataTable({
        "pageLength": 5,
        "language": {
            search: "Search:",
            searchPlaceholder: "Find books by title and/or author"
        },
        "columnDefs": [
            { "searchable": false, "targets": [0,3,4,5,6] },
            { "orderable": true, "targets": [1,2,3,4,5]},
            {"orderable": false, "targets": [0, 6]}
        ],
        lengthMenu:  [
            [2 , 5 , 10, 20, -1],
            [2, 5 , 10, 20, 'All'],
        ],
        order: [1, 'asc']
    });


    $('input.filter-publishers').on('change', function() {
        table.fnDraw();
    });

    $.fn.dataTable.ext.search.push(
        function(settings, searchData, index, rowData) {
            // No filtered checked - show all rows
            if ($('.filter-publishers:checked').length === 0) {
                return true;
            }

            // Filter(s) checked, apply logic
            var found = false;
            $('.filter-publishers').each(function(index, elem) {
                if (elem.checked && rowData[3] === elem.value) {
                    found = true;
                }
            });

            return found;
        }
    );
});
