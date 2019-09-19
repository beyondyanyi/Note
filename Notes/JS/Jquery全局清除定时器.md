#### 设置定时器，要先清除定时器

```
$.extend({
    seI_timeout: false,
    seI_autoFn: null,//定时器
    autoFnIndex: 0,//定时器执行的次数，可判断执行某些东西
    autoFn: function () {//如果有需要，加参数
        getRealTimeData();
        //每次清空定时器
        clearTimeout($.seI_autoFn);
        //定时器更新时间
        var refreshTime = 1000 * 60 * 5;
        if (!$.seI_timeout) {
            $.seI_autoFn = setTimeout(function () {
                $.autoFn();
            }, refreshTime);
        }
    }
});
$.autoFn();
```

