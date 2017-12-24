$(function () {
    var listUrl='/o2o/shopadmin/getproductcategorylist';
    var addUrl = '/o2o/shopadmin/addproductcategorys';
    var deleteUrl='/o2o/shopadmin/removeproductcategorys';
    var shopId = getQueryString('shopId');
    $('#backBtn').attr('href','/o2o/shopadmin/shopmanagement?shopId=' + shopId);
    getCategoryList();
    function getCategoryList(e) {
        $.ajax({
            url:listUrl+"?shopId="+shopId,
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.success)
                {
                    $('.category-wrap').html('');
                    handleList(data.productCategoryList);
                }
            }
        });
    }

    function handleList(data) {
        var html='';
        data.map(function (item, index) {
            html+='<div class="row row-product-category now"><div class="col-40">'
                +item.productCategoryName+'</div> <div class="col-40">'
                +item.priority
                +'</div><div class="col-20">'
                +'<a href="#" id="deleteBtn" data-id="'+item.productCategoryId+'" class="button delete button-link">' +
                '            <span class="icon"></span>' +
                '            删除' +
                '        </a>'
                +'</div></div>'
        });
        $('.category-wrap').append(html);
    }

    $('#new').click(function () {
        var tempHtml = '<div class="row row-product-category temp">'
            + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类"></div>'
            + '<div class="col-33"> <input class="category-input priority" type="number" placeholder="优先级"></div>'
            + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
            + '</div>';
        $('.category-wrap').append(tempHtml);
    });

    $('#submit').click(function () {
        var tempArr=$('.temp');
        var productCategoryList=[];
        tempArr.map(function (index, item) {
            var tempObj={};
            tempObj.productCategoryName=$(item).find('.category').val();
            tempObj.priority=$(item).find('.priority').val();
            if(tempObj.productCategoryName&&tempObj.priority)
            {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url:addUrl,
            type:'POST',
            data:JSON.stringify(productCategoryList),
            contentType:'application/json',
            success:function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                    getCategoryList();
                } else {
                    $.toast('提交失败！');
                }
            }
        });
    });

    $('.category-wrap').on('click','.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();
        });

    $('.category-wrap').on('click','.row-product-category.now .delete',
        function (e) {
            var target=e.currentTarget;
            $.confirm('确定吗',function () {
                $.ajax({
                    url:deleteUrl,
                    type:'POST',
                    data:{
                        productCategoryId:target.dataset.id
                    },
                    dataType:'json',
                    success:function (data) {
                        if(data.success)
                        {
                            $.toast('删除成功!');
                            getCategoryList();
                        }else{
                            $.toast('删除失败!');
                        }
                    }
                });
            });
        });
});
