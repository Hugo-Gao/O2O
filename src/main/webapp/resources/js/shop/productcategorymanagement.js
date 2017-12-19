$(function () {
    var shopId = getQueryString('shopId');
    $('#backBtn').attr('href','/o2o/shopadmin/shopmanagement?shopId=' + shopId);
    getCategoryList();
    function getCategoryList(e) {
        $.ajax({
            url:"/o2o/shopadmin/getproductcategorylist?shopId="+shopId,
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.success)
                {
                    handleList(data.productCategoryList);
                }
            }
        });
    }

    function handleList(data) {
        var html='';
        data.map(function (item, index) {
            html+='<div class="row row-product-category"><div class="col-40">'
                +item.productCategoryName+'</div><div class="col-40">'
                +item.priority
                +'</div><div class="col-20">'
                +'<a id="deleteBtn" class="button button-link">' +
                '            <span class="icon"></span>' +
                '            删除' +
                '        </a>'
                +'</div></div>'
        });
        $('.category-wrap').html(html);
    }
});