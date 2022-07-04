$(document).ready( function () {
    $('#bookTable').DataTable({
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
});