netsh interface ipv4 set address WLAN static 192.168.1.123 255.255.255.0 192.168.1.1 1  
netsh interface ipv4 set dns name="WLAN" source=static addr=114.114.114.114 register=PRIMARY 
