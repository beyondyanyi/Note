title 容今慎白字送
ffmpeg -i "rtsp://admin:xiongan123@172.16.2.64:554/Streaming/Channels/1" -g 10  -vcodec  libx264  -acodec copy -r 10 -f flv rtmp://localhost:1935/live/haikang