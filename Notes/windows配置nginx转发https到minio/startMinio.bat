cd C:/minio/bin  
set MINIO_ROOT_USER=minio  
set MINIO_ROOT_PASSWORD=mw_123456
 
minio.exe server C:\minio\data --console-address ":9000"  --address ":9001"
