$(function ()
{
    var productId = getQueryString('productId');
    var getProductDetailUrl = '/o2o/frontend/getproductdetail?productId=' + productId;
    getProductDetail();


    function getProductDetail()
    {
        var url = getProductDetailUrl;
        $.getJSON(url, function (data)
        {
            if (data.success) {
                var product = data.product;
                $('#product-name').html(product.productName);
                $('#product-cover-pic').attr('src', product.imgAddr);
                $('#product-update-time').html(new Date(product.lastEditTime)
                    .Format("yyyy-MM-dd"));
                if(product.normalPrice!==undefined&&product.promotionPrice!==undefined)
                {
                    $('#product-normal-price').html('<del>'+'￥'+product.normalPrice+'</del>');
                    $('#product-promotion-price').text('￥'+product.promotionPrice);
                }
                else {
                    $('#product-normal-price').html('￥'+product.normalPrice);
                }
                $('#price').show();
                $('#product-description').html(product.productDesc);
                var productImgList = data.productImgList;
                var html = '';
                productImgList.map(function (item, index)
                {
                    html+='<div><img src="'+item.imgAddr+'"></div>'
                });
                $('.list-div').append(html);
            }
        });
    }
});