FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(VerifyYourPhoneNumber.this, Home.class);
            startActivity(intent);
            finish();
        }
