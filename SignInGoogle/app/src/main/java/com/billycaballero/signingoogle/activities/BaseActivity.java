package com.billycaballero.signingoogle.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.billycaballero.signingoogle.R;
import com.billycaballero.signingoogle.util.Constans;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

/**
 * Created by billycaballero on 10/23/15.
 */
public class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    protected static Toolbar mToolbar;

    /* Request code used to invoke sign in user interactions. */
    public static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    protected static GoogleApiClient mGoogleApiClient;

    /* Is there a ConnectionResult resolution in progress? */
    protected static boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    protected static boolean mShouldResolve = false;

    public static final int RC_PERM_GET_ACCOUNTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

    }

    public void onSignOutClicked() {
        // Clear the default account so that GoogleApiClient will not automatically
        // connect in the future.
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }

        showSignedOutUI();
    }

    public void onDisconnectClicked() {
        // Revoke all granted permissions and clear the default account.  The user will have
        // to pass the consent screen to sign in again.
        Log.v(TAG, "onDisconnectClicked");
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }

        showSignedOutUI();
        goToLoginActivity();
    }


    public void showSignedInUI() {
        updateUI(true);
    }

    public void showSignedOutUI() {
        updateUI(false);
    }

    public void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
            Log.v(TAG, "USER EMAIL :" + email);

            if (currentPerson != null) {
                // Show signed-in user's name
                String name = currentPerson.getDisplayName();

                // Show users' email address (which requires GET_ACCOUNTS permission)
                if (checkAccountsPermission()) {
                    String currentAccount = Plus.AccountApi.getAccountName(mGoogleApiClient);
                }

                String personPhoto = currentPerson.getImage().getUrl().split("=")[0] + "=250";

                Log.v(TAG, "USER NAME :" + name);
                Log.v(TAG, "USER PHOTO :" + personPhoto);

                startUserActivity(name, email, personPhoto);

            } else {
                // If getCurrentPerson returns null there is generally some error with the
                // configuration of the application (invalid Client ID, Plus API not enabled, etc).
                Log.v(TAG, "USER RETURN NULL");
            }

        } else {
            // Deslogeado
            Log.v(TAG, "USER DESLOGEADO");
        }
    }

    public void startUserActivity(String name, String email, String urlImage){
        Intent intent = new Intent(getBaseContext(), UserActivity.class);
        intent.putExtra(Constans.USER_FULL_NAME, name);
        intent.putExtra(Constans.USER_EMAIL, email);
        intent.putExtra(Constans.USER_IMAGE, urlImage);
        startActivity(intent);
    }

    public void goToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Check if we have the GET_ACCOUNTS permission and request it if we do not.
     * @return true if we have the permission, false if we do not.
     */
    public boolean checkAccountsPermission() {
        final String perm = Manifest.permission.GET_ACCOUNTS;
        int permissionCheck = ContextCompat.checkSelfPermission(this, perm);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed.
            Snackbar.make(findViewById(R.id.main_layout),
                    R.string.contacts_permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Request the permission again.
                            ActivityCompat.requestPermissions(BaseActivity.this,
                                    new String[]{perm},
                                    RC_PERM_GET_ACCOUNTS);
                        }
                    }).show();
            return false;
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{perm},
                    RC_PERM_GET_ACCOUNTS);
            return false;
        }
    }

    public void showErrorDialog(ConnectionResult connectionResult) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, RC_SIGN_IN,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                mShouldResolve = false;
                                showSignedOutUI();
                            }
                        }).show();
            } else {
                Log.w(TAG, "Google Play Services Error:" + connectionResult);
                String errorString = apiAvailability.getErrorString(resultCode);
                Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show();

                mShouldResolve = false;
                showSignedOutUI();
            }
        }
    }

}
