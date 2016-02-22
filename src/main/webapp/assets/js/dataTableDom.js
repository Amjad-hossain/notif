/**
 * Init data table dom for list view
 * @param tblIdOrCls
 * @param ajaxUri
 * @param columns
 */
function tableInit( tblIdOrCls, ajaxUri, columns) {

    $(tblIdOrCls).DataTable( {

        "lengthMenu": [[5, 10, 20], [5, 10, 20]],
        "displayLength": 10,
        "processing": true,
        "serverSide": true,
        "autoWidth": true,
        "paginationType": "simple_numbers", // you can also give here 'simple','simple_numbers','full','full_numbers'
        "language": {
            "search": "Key:"
        },
        "ajax": ajaxUri,
        "columns": columns
    } );
}
