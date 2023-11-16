package com.example.wallet.login;

import android.os.AsyncTask;

public class CredentialsValidation {

    public interface OnValidationResult {
        void onResult(boolean isValid);
    }

    public static void validateCredentials(final String username, final String password, final OnValidationResult callback) {
        // Simulating an asynchronous validation process, such as a network call
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return "admin".equals(username) && "123456".equals(password);
            }

            @Override
            protected void onPostExecute(Boolean isValid) {
                callback.onResult(isValid);
            }
        }.execute();
    }
}
