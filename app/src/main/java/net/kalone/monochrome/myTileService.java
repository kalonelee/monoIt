package net.kalone.monochrome;

import android.Manifest;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by Kalone on 12/21/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class myTileService extends android.service.quicksettings.TileService {
    @Override
    public void onTileAdded(){
        super.onTileAdded();
        Toast.makeText(this, String.format("%s%s\n%s%s", getText(R.string.manufacturer), Build.MANUFACTURER,getText(R.string.model),Build.MODEL), Toast.LENGTH_SHORT).show();
        Tile tile = getQsTile();
        tile.setIcon(Icon.createWithResource(this,
                R.drawable.ic_invert_colors_black_24dp));
        tile.setLabel(getString(R.string.app_name));
        tile.setContentDescription(getString(R.string.description));
        try {
            boolean monochromeOn = Settings.Secure.getInt(getContentResolver(), "accessibility_display_daltonizer_enabled") == 1;
            if(monochromeOn)
                tile.setState(Tile.STATE_ACTIVE);
            else
                tile.setState(Tile.STATE_INACTIVE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            tile.setState(Tile.STATE_INACTIVE);
        }
        tile.updateTile();
    }
    @Override
    public void onTileRemoved(){
        super.onTileRemoved();
        boolean writeSecurePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
        boolean writeSystemPermisson=ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
        boolean byOnePlus = Build.MANUFACTURER.equals("OnePlus") ;
        boolean OnePlus3x= Build.MODEL.length()>10 ? Build.MODEL.substring(0,10).equals("ONEPLUS A3") : false;
        if(writeSecurePermission) {
            try {
                boolean monochromeOn = Settings.Secure.getInt(getContentResolver(), "accessibility_display_daltonizer_enabled") == 1;
                if (monochromeOn) {
                    if(byOnePlus && writeSystemPermisson) {
                        Settings.System.putInt(getContentResolver(), "reading_mode_status_manual", 0);
                        if(OnePlus3x) {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 0);
                                }

                            }, 1200);
                        }
                    }
                    else {
                        Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 0);
                    }
                }
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onClick(){
        super.onClick();
        boolean writeSecurePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
        boolean writeSystemPermisson=ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
        boolean byOnePlus = Build.MANUFACTURER.equals("OnePlus") ;
        boolean OnePlus3x= Build.MODEL.length()>10 ? Build.MODEL.substring(0,10).equals("ONEPLUS A3") : false;
        Tile tile = getQsTile();
        if(writeSecurePermission) {
            try {
                boolean monochromeOn = Settings.Secure.getInt(getContentResolver(), "accessibility_display_daltonizer_enabled") == 1;
                if (monochromeOn) {
                    if(byOnePlus && writeSystemPermisson) {
                        Settings.System.putInt(getContentResolver(), "reading_mode_status_manual", 0);
                        if(OnePlus3x) {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 0);
                                }

                            }, 1200);
                        }
                    }
                    else {
                        Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 0);
                    }
                    tile.setState(Tile.STATE_INACTIVE);
                    tile.setLabel(getString(R.string.app_name));
                }
                else{
                    if(byOnePlus && writeSystemPermisson) {
                        Settings.System.putInt(getContentResolver(), "reading_mode_status_manual", 1);
                        if(OnePlus3x){
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 1);
                                }

                            }, 900);
                        }
                    }
                    else{
                        Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 1);
                    }
                    tile.setState(Tile.STATE_ACTIVE);
                    tile.setLabel(getString(R.string.color_it));
                }
                tile.updateTile();
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, getText(R.string.error), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, getString(R.string.no_permisson), Toast.LENGTH_LONG).show();
            ClipboardManager clipboard = (ClipboardManager)
                    getSystemService(this.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", String.format("%s\n%s",getText(R.string.command_helper),getText(R.string.command)));
            clipboard.setPrimaryClip(clip);
        }
    }
}
