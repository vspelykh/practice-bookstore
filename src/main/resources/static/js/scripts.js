$(document).ready(function() {

    var table = $('#bookTable').dataTable({
        "pageLength": 5,
        "language": {
            search: "Search:",
            searchPlaceholder: "Find books by title and/or author"
        },
        "columnDefs": [
            {
                target: [7,8],
                visible: false,
                searchable: false
            },
            { "searchable": false, "targets": [0,3,4,6] },
            { "orderable": true, "targets": [1,2,3,4,5]},
            {"orderable": false, "targets": [0, 6]}
        ],
        lengthMenu:  [
            [2 , 5 , 10, 20, -1],
            [2, 5 , 10, 20, 'All'],
        ],
        order: [1, 'asc'],

    });

    $('#min, #max').on('change', function (){
        table.fnDraw();
    });

    $('input.filter-categories').on('change', function (){
        table.fnDraw();
    });

    $('input.filter-subCategories').on('change', function (){
        table.fnDraw();
    });

    $('input.filter-publishers').on('change', function() {
        table.fnDraw();
    });

    $.fn.dataTable.ext.search.push(
        function (settings, data, dataIndex) {
            var min = parseInt($('#min').val(), 10);
            var max = parseInt($('#max').val(), 10);
            var age = parseFloat(data[5]) || 0; // use data for the age column

            if (
                (isNaN(min) && isNaN(max)) ||
                (isNaN(min) && age <= max) ||
                (min <= age && isNaN(max)) ||
                (min <= age && age <= max)
            ) {
                return true;
            }
            return false;
        },

        function categories(settings, searchData, index, rowData) {
            // No filtered checked - show all rows
            if ($('.filter-categories:checked').length === 0) {
                return true;
            }

            // Filter(s) checked, apply logic
            var found = false;
            $('.filter-categories').each(function(index, elem) {
                if (elem.checked && rowData[7] === elem.value) {
                    found = true;
                }

            });

            return found;
        },
        function subCategories(settings, searchData, index, rowData) {
            // No filtered checked - show all rows
            if ($('.filter-subCategories:checked').length === 0) {
                return true;
            }

            // Filter(s) checked, apply logic
            var found = false;
            $('.filter-subCategories').each(function(index, elem) {
                if (elem.checked && rowData[8] === elem.value) {
                    found = true;
                }
            });

            return found;
        },

        function publishers(settings, searchData, index, rowData) {
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


//cart
$(document).ready(function() {

    var table = $('#cartTable').dataTable({
        "pageLength": -1,
        "language": {
            search: false
        },
        "columnDefs": [
            { "searchable": false},
            {"orderable": true, "targets": [1,2,3,4,5,6]}
        ],
        lengthMenu:  [
            [2 , 5 , 10, 20, -1],
            [2, 5 , 10, 20, 'All'],
        ],
        order: [1, 'asc']
    });
});