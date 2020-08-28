package vn.com.fast.qrcode.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.com.fast.qrcode.R;
import vn.com.fast.qrcode.View.Login.ForgotPasswordFragment;
import vn.com.fast.qrcode.View.Login.LoginFragment;
import vn.com.fast.qrcode.View.Login.SettingLoginFragment;

public class LoginActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    CardView login_cardview;
    TextView login_tvForgotPassword;
    ProgressBar login_progressBar;
    ImageButton login_btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_cardview = findViewById(R.id.login_cardview);
        login_tvForgotPassword = findViewById(R.id.login_tvForgotPassword);
        login_progressBar = findViewById(R.id.login_progressBar);
        login_btnSetting = findViewById(R.id.login_btnSetting);

        addFragment(new LoginFragment(), "login");

        login_cardview.setElevation(0.0f);
        login_tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentCustomAnimations(new ForgotPasswordFragment(), "forgot");
                login_tvForgotPassword.setVisibility(View.INVISIBLE);
                login_btnSetting.setVisibility(View.INVISIBLE);
            }
        });
        login_progressBar.setVisibility(View.INVISIBLE);
        login_btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentCustomAnimations(new SettingLoginFragment(), "setting");
                login_btnSetting.setVisibility(View.INVISIBLE);
                login_tvForgotPassword.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void addFragment(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.login_framelayout, fragment, tag)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void replaceFragmentCustomAnimations(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .replace(R.id.login_framelayout, fragment, tag)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
            login_tvForgotPassword.setVisibility(View.VISIBLE);
            login_btnSetting.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }
}