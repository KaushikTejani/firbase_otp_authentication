//    Send Verification Code
    public void sendVerificationCode() {
        // Send Otp
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mMobileNumberString,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                EditText OTP=findViewById(R.id.getOtp_editText_2);
                        OTP.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toasty.error(VerifyPhoneNumber.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toasty.success(VerifyPhoneNumber.this, "OTP is Send Pleas Verify with in 60 Second.", Toast.LENGTH_SHORT).show();
            mVerificationId = s;
        }
    };
