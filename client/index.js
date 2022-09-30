const { app, BrowserWindow } = require('electron');
const path = require('path');

// windowsOption
function createWindow() {
  const mainWindow = new BrowserWindow({ resizable: false, fullscreen: true, autoHideMenuBar: true });
  mainWindow.loadFile('main.html');
}

// entryPoint
app.whenReady().then(() => {
  createWindow();
  app.on('activate', function () {
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

// closed
app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') app.quit();
});
