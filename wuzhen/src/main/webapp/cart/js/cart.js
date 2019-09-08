
var vm = new Vue({
    el: '#app',
    data: {
        uri:'showRecord',
        user:{name:'',password:0},
        totalMoney: 0,
        productList: [],

        checkAllFlag: false,
        curProduct: '',
        delFlag: false,
        oid:0,
        total:0
    },
    /*filters: {
        formatMoney: function (value) {
            return "¥ " + value.toFixed(2) + " 元";
        }
    },*/
    mounted: function () {
        this.$nextTick(function () {
            this.cartView();
        });
    },
    methods: {
        cartView: function () {
            axios.get(this.uri).then(function (response) {
                vm.productList=response.data;
            })
            /*var this = this;
            this.$http.get("data/cartData.json", {"id": 123}).then(function (res) {
                this.productList = res.body.result.list;
            })*/
        },
        //改变产品数量
        changeMoney: function (record, way) {
            var url;

            if (way > 0) {
                record.number++;
                url = "changeNum?pid="+record.product.id+"&num="+record.number;
                axios.get(url);
            } else {
                record.number--;
                if (record.number < 0) {
                    record.number = 0;
                }else {
                    url = "changeNum?pid="+record.product.id+"&num="+record.number;
                    axios.get(url);
                }
            }
            this.caleTotalPrice();
        },
        selectedProduct: function (item) {
            if (typeof item.checked == 'undefined') {
                Vue.set(item, "checked", true);
            } else {
                item.checked = !item.checked;
            }
            this.caleTotalPrice();
        },
        checkAll: function (flag) {
            this.checkAllFlag = flag;
            this.productList.forEach(function (item, index) {
                console.log(this)
                console.log(item)
                if (typeof item.checked == 'undefined') {
                    Vue.set(item, "checked", vm.checkAllFlag);
                } else {
                    item.checked = vm.checkAllFlag;
                }
            });
            this.caleTotalPrice();
        },
        caleTotalPrice: function () {
            vm.totalMoney=0;
            this.productList.forEach(function (record, index) {
                if (record.checked) {
                    vm.totalMoney += record.product.price * record.number;
                }
            });
        },
        delConfirm: function (item) {
            this.delFlag = true;
            this.curProduct = item;
        },
        //删除购物车商品方法
        delProduct: function () {
            var index=this.curProduct.id;
           var url = "delProduct?rid="+index;
            axios.delete(url);
            location.href="cart"
        },
        check: function () {
            var str1='';
            this.productList.forEach(function (item, index) {
                if(item.checked == true){
                         str1+='oid='+item.id+"&";
                }
            });
            var url="check?"+str1;
            url=url.substr(0,url.length-1);
            location.href=url;
        }
    }
});