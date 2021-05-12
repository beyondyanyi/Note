title 推宇视频枪机流
ffmpeg -i "rtsp://admin:admin@172.16.2.67/video1" -g 10 -vcodec libx264   -acodec copy -r 10 -f flv rtmp://localhost:1935/live/yushi