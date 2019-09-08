var vm = new Vue({
    el: '#app',
    data: {
        uri:'forebuy',
        total:0,
        records:[],
        order:{address:'',post:'',receiver:'',mobile:'',userMessage:'',orderId:0}
    },
    mounted: function () {
        this.$nextTick(function () {
            this.checkView();
        });
    },
    methods: {
        checkView: function () {
            var oid = getUrlParms("oid");
             var url =  this.uri+"/?oid="+oid;
            axios.get(url).then(function(response) {
                var result = response.data;
               vm.total = result.data.total;
                 vm.records = result.data.records;
             });
        },
        changeMoney: function (record, way) {
            var url;
            if (way > 0) {
                record.number++;
                url = "changeNum?pid="+record.product.id+"&num="+record.number;
                axios.get(url);

            } else {
                record.number--;
                if (record.number < 1) {
                    record.number = 1;
                }else {
                    url = "changeNum?pid="+record.product.id+"&num="+record.number;
                    axios.get(url);
                }
            }
            this.caleTotalPrice();
        },
        caleTotalPrice: function () {
            vm.total=0;
            this.records.forEach(function (record, index) {
                    vm.total += record.product.price * record.number;
            });
        },
        createOrder:function () {
            var url="forecreateOrder";
            axios.post(url,this.order).then(function(response) {
                var result=response.data;
                vm.order.orderId=result.data.orderId;
                swal({
                    title: '成功生成订单',
                    text: '请支付'+vm.total+"￥",
                    showCancelButton: true,
                    imageUrl: 'img/productSingle/1.jpg',
                    imageWidth: 400,
                    imageHeight: 200,
                    confirmButtonText: '确认购买',
                    cancelButtonText: '取消订单',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: false
                }).then(function(isConfirm) {
                    if (isConfirm){
                        swal({
                                text:"购买成功",
                                type:"success"
                        }).then(function () {
                            location.href="cart"
                        });
                        //后台删除订单中购物车的数据，修改订单状态 购买成功
                        console.log(vm.order);
                        axios.get("forepayed?oid="+ vm.order.orderId);
                    }
                    else{
                        swal({
                            text:"付款失败",
                            type:'error'
                        });

                    }
                })
            });
        }
    }
});