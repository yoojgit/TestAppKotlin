package com.androidpractice.testappkotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    val TAG = "로그";
    // Access a Cloud Firestore instance from your Activity
    //val db = Firebase.firestore

    var sName: String?= null
    var sEmail: String?=null
    var sPassword: String?=null
    //name, email, password 값 가져와서 보여주기

    //private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Initialize Firebase Authentication
        //auth = Firebase.auth

            //if true then proceed inserting data to db
            btnRegisterView.setOnClickListener {
                //OldRegisterMethod()

                var IsNotNull =  ValidateTextbox()

                if(IsNotNull){
                    AuthRegisterMethod(sEmail.toString(), sPassword.toString())
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = ResourceMain.getInstance().mAuth.currentUser
        //updateUI(currentUser)
    }

    fun AuthRegisterMethod(email:String, password:String){
        ResourceMain.getInstance().mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = ResourceMain.getInstance().mAuth.currentUser

                    val dataUserRegister = hashMapOf(
                        "name" to sName,
                        "email" to sEmail,
                        "password" to sPassword
                    )

                    ResourceMain.getInstance().db.collection("Users").document(user?.uid.toString())
                        .set(dataUserRegister)
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

//                        .addOnSuccessListener{
//                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                        }
//                        .addOnFailureListener {
//                                e ->
//                            Log.w(TAG, "Error adding document", e)
//                        }
                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@RegisterActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth )
                        .setTitle("인증실패")
                        .setMessage(task.exception?.message)
                        .setNeutralButton("확인", DialogInterface.OnClickListener{ dialog, which ->
                        })
                    alertDialog.show()
                }
            }
    }

    fun updateUI(user:FirebaseUser?=null){
        if(user != null){
            // register success alert dialog shown
            // and return to main activity intent
            val intentMain = Intent(this, MainActivity::class.java)
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@RegisterActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth )
                .setTitle("가입성공")
                .setMessage("환영합니다!")
                .setNeutralButton("확인", DialogInterface.OnClickListener{
                        dialog, which ->
                    startActivity(intentMain)
                    finish()
                })
            alertDialog.show()
        }else{
            Toast.makeText(baseContext, "fail!!", Toast.LENGTH_SHORT).show()
        }
    }


    fun  OldRegisterMethod(){
        var IsNotNull =  ValidateTextbox()

        if(IsNotNull){
            val dataUserRegistration = hashMapOf(
                "name" to sName,
                "email" to sEmail,
                "password" to sPassword
            )


            ResourceMain.getInstance().db.collection("Users")
                .add(dataUserRegistration)
                .addOnSuccessListener {
                        documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener {
                        e ->
                    Log.w(TAG, "Error adding document!", e)
                }



            // register success alert dialog shown
            // and return to main activity intent
            val intentMain = Intent(this, MainActivity::class.java)
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@RegisterActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth )
                .setTitle("가입성공")
                .setMessage("환영합니다!")
                .setNeutralButton("확인", DialogInterface.OnClickListener{ dialog, which ->

                    startActivity(intentMain)
                    finish()
                })
            alertDialog.show()


        }else{
            Toast.makeText(this@RegisterActivity, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }
    }



    fun ValidateTextbox() : Boolean{

        var bIsAllNotNull: Boolean = false;

        if(editTextTextEmail.text.length <= 0){
            Toast.makeText(this@RegisterActivity, "이메일 필수!", Toast.LENGTH_SHORT).show();
        }else{
            sName = editTextTextPersonName.text.toString()
        }

        if(editTextTextPersonName.text.length <= 0){
            Toast.makeText(this@RegisterActivity, "이름 필수!", Toast.LENGTH_SHORT).show()
        }else{
            sEmail = editTextTextEmail.text.toString()
        }

        if(editTextTextPassword.text.length <= 0){
            Toast.makeText(this@RegisterActivity, "비밀번호 필수", Toast.LENGTH_SHORT).show()
        }else{
            sPassword = editTextTextPassword.text.toString()
        }

        if(!sName.isNullOrEmpty() && !sEmail.isNullOrEmpty() && !sPassword.isNullOrEmpty()){
            bIsAllNotNull = true
        }else{
            bIsAllNotNull = false
        }

        return bIsAllNotNull
    }
}