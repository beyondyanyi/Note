#### 1.对象深拷贝（拷贝对象值，不是引用）

```
	 this.foo= Object.assign({}, destObj)
```



#### 2.Vue script部分

```
<script>
export default {
  name: "Home",
  data() {
    return {};
  },
  methods: {
    // 组件的方法
  },
  watch: {
    // watch擅长处理的场景：一个数据影响多个数据
  },
  computed: {
    // computed擅长处理的场景：一个数据受多个数据影响
  },
  beforeCreate: function() {
    // 在实例初始化之后，数据观测(data observer) 和 event/watcher 事件配置之前被调用。
  },
  created: function() {
    // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见。
  },
  beforeMount: function() {
    // 在挂载开始之前被调用：相关的 render 函数首次被调用。
  },
  mounted: function() {
    // 编译好的HTML挂载到页面完成后执行的事件钩子
    // el 被新创建的 vm.$el 替换，并挂载到实例上去之后调用该钩子。
    // 此钩子函数中一般会做一些ajax请求获取数据进行数据初始化
    console.log("Home done");
  },
  beforeUpdate: function() {
    // 数据更新时调用，发生在虚拟 DOM 重新渲染和打补丁之前。 你可以在这个钩子中进一步地更改状态，这不会触发附加的重渲染过程。
  },
  updated: function() {
    // 由于数据更改导致的虚拟 DOM 重新渲染和打补丁，在这之后会调用该钩子。
    // 当这个钩子被调用时，组件 DOM 已经更新，所以你现在可以执行依赖于 DOM 的操作。然而在大多数情况下，你应该避免在此期间更改状态，因为这可能会导致更新无限循环。
    // 该钩子在服务器端渲染期间不被调用。
  },
  beforeDestroy: function() {
    // 实例销毁之前调用。在这一步，实例仍然完全可用。
  },
  destroyed: function() {
    // Vue 实例销毁后调用。调用后，Vue 实例指示的所有东西都会解绑定，所有的事件监听器会被移除，所有的子实例也会被销毁。 该钩子在服务器端渲染期间不被调用。
  }
};
</script>
```

