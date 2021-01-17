package com.androidpractice.testappkotlin

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    val TAG = "로그";

    // Access a Cloud Firestore instance from your Activity
    //val db = Firebase.firestore

    var sEmail:String?=null
    var sPassword:String ?= null
    //private lateinit var auth: FirebaseAuth

    var isLoginSuccess:Boolean?= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Initialize Firebase Authentication
        //auth = Firebase.auth

        btnLogin.setOnClickListener {

            var Checked:Boolean = DoValidation()

            if(Checked){

                //do
                ResourceMain.getInstance().mAuth.signInWithEmailAndPassword(sEmail.toString(),sPassword.toString())
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful){
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = ResourceMain.getInstance().mAuth.currentUser
                            isLoginSuccess = true

                            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@LoginActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth )
                                .setTitle("로그인성공")
                                .setMessage("로그인에 성공했다")
                                .setNeutralButton("확인", DialogInterface.OnClickListener{
                                        dialog, which ->
                                        updateUI(user)
                                })
                            alertDialog.show()
                        }else{
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()

                            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@LoginActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth )
                                .setTitle("인증실패")
                                .setMessage(task.exception?.message)
                                .setNeutralButton("확인", DialogInterface.OnClickListener{
                                        dialog, which ->
                                })
                            alertDialog.show()

                            isLoginSuccess = false
                            updateUI(null)
                        }
                    }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = ResourceMain.getInstance().mAuth.currentUser
        //updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?=null){
        var name:String ?= null
        var email:String ?= null
        var photoUrl: Uri?= null
        var emailVerified:Boolean?=null

        var uid:String?=null

        if(isLoginSuccess == true){
            val docRef = ResourceMain.getInstance().db.collection("Users").document(user?.uid.toString())
            docRef.get()
                .addOnSuccessListener {
                    document ->
                    if(document != null){
                        Log.d(TAG, "DocumentSnapshop data: ${document.data}")
                        email = document.getString("email")
                        name = document.getString("name")
                        //document.getString("password")

                        val intentMainMasterActivity = Intent(this, MainMasterActivity::class.java)
                        intentMainMasterActivity.putExtra("user_display_name", name)
                        intentMainMasterActivity.putExtra("user_email", email)
                        intentMainMasterActivity.putExtra("user_uid", uid)
                        //intentMainMasterActivity.putExtra("user_email", user?.email.toString())
                        startActivity(intentMainMasterActivity)

                    }else{
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener{
                        exception ->
                    Log.d(TAG, "get failed with ", exception)
                }


//            //if user != null
//            user?.let{
//                name = user.displayName
//                email = user.email
//                photoUrl = user.photoUrl
//                //check if user's email is verified
//                 emailVerified = user.isEmailVerified
//                //the user's ID, unique to the firebase project. do not  user this value to authenticate with your backend server, if you have one. use FirebaseUser.getToken() instead.
//                uid = user.uid
//            }
        }
    }

    fun DoValidation():Boolean{
        var Checked:Boolean = false
        if(tbEmail.text.length <= 0){
            Toast.makeText(this@LoginActivity, "이메일 필수!", Toast.LENGTH_SHORT).show();
        }else{
            //check if this email really exist in db
            sEmail = tbEmail.text.toString()

//            //단일 문서만 가져올것...
//            val getUserInfo = db.collection("Users").document()
//
//            db.collection("Users")
//                .get()
//                .addOnSuccessListener {
//                    result ->
//                    for(document in result){
//                        //Log.d(TAG, "#{document.id} => ${document.data}")
//                        if(document.data.equals(tbEmail)){
//                            sEmail = document.data.toString() //입력한 이메일 값이랑 루프값이랑 같으면 할당.
//                            break
//                        }
//                        else{
//                            Toast.makeText(this@LoginActivity, "${tbEmail.text.toString()} NOT EXIST!!", Toast.LENGTH_SHORT).show()
//                            break
//                        }
//                    }
//                }
//                .addOnFailureListener{
//                    ex ->
//                    Log.w(TAG, "Error getting docuemnts!!!", ex)
//                }
        }

        if(tbPassword.text.length <=0){
            Toast.makeText(this@LoginActivity, "비밀번호 필수", Toast.LENGTH_SHORT).show()
        }else{
            //check if this password matches with the email
            sPassword = tbPassword.text.toString()
        }
        if(!sEmail.isNullOrEmpty() && !sPassword.isNullOrEmpty()){
            Checked = true
        }else{
            Checked = false
        }
        return Checked
    }

}