## Start HUB

## MacOS
```
java -cp selenium-server-standalone-3.141.59.jar:selenium-grid-custom-matcher-3.141.59.jar org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig hubConfig.json
```

## Windows
```
java -cp "selenium-server-standalone-3.141.59.jar;selenium-grid-custom-matcher-3.141.59.jar" org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig hubConfig.json
```

## Start Node 01
```
appium -p 6000 --nodeconfig node_6000_config.json
```

## Start Node 02
```
appium -p 7000 --nodeconfig node_7000_config.json
```