1.

```
传值：
this.$Event.$emit('test', this.type)
取值：
mounted () {
    this.$Event.$on('test', data => {
      console.log(data)
    })
}
销毁：（建议卸载beforeDestroy里面）
this.$Event.$off('test');
```

2.

```
传值：
this.$store.commit('setType', 'vvvvvv')
取值：
watch: {
    '$store.state.type': function (val, oldVal) {
    console.log(val)
    this.$store.commit('setType', val)
    }
}

store.js设置：
export default new Vuex.Store({
  state: {
    type: 'bbbbb'
  },
  mutations: {
    setType (state, val) {
      state.type = val
    }
  },
  actions: {

  }
})

```

 