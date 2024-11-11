参考[web端实现rtsp实时推流视频播放可行性方案分析_web推流_【零声教育】音视频开发进阶的博客-CSDN博客](https://blog.csdn.net/irainsa/article/details/130014024)







1.搭建 webrtc-streamer服务端

源码地址：

https://github.com/mpromonet/webrtc-streamer





2.将下载包html文件夹下webrtcstreamer.js文件和html/libs文件夹下adapter.min.js文件复制到VUE项目public目录下。在index.html文件里引入这两个js文件。

index.html

```
 <script type="text/javascript" src="./adapter.min.js"></script>
 <script type="text/javascript" src="./webrtcstreamer.js"></script>
```

3.编写测试页面

```

<template>
  <div>
    <a-button @click="handleChange">切换</a-button>
    <video id="video" autoplay width="900" height="900"></video>
  </div>
</template>
 
<script>
export default {
  name: 'index1',
  data() {
    return {
      webRtcServer: null//webRtcServer上下文
    }
  },
  mounted() {
    //video：需要绑定的video控件ID
    //127.0.0.1:8000：启动webrtc-streamer的设备IP和端口，默认8000
    this.webRtcServer = new WebRtcStreamer('video', location.protocol + '//127.0.0.1:8000')
    //需要查看的rtsp地址,地址为财物系统地址
    this.webRtcServer.connect('rtsp://admin:admin@192.168.23.80:554/1/1')
  },
  beforeDestroy() {
    this.webRtcServer.disconnect()
    this.webRtcServer = null
  },
  methods: {
    /**
    * 有多个视频源的情况下，直接调用服务的connect方法即可
    */
    handleChange() {
      this.webRtcServer.connect('rtsp://admin:admin@192.168.23.80:554/1/1')
    }
  }
}
</script>
 
<style scoped></style>

```

**方案结论：**

- 性能好，延迟低（实际测试是毫秒级别），实时性高

- 同时播放多个视频源后端的cpu占用率挺高，目前不知道是啥原因

- 目前为止已知的最好方案是WebRTC方案，阿里云提供的相关服务就是采用了这一方案

# 
