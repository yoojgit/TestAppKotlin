package com.androidpractice.testappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "로그";

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore

        btnLoginGoogle.setOnClickListener {

//            //데이터 추가 insert to db
//            // Create a new user with a first and last name
//            val user = hashMapOf(
//                "first" to "Ada",
//                "last" to "Lovelace",
//                "born" to 1815
//            )
//            //Add a new document with a generated ID
//            db.collection("users").add(user)
//                .addOnSuccessListener {
//                documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener {
//                    e -> Log.w(TAG, "Error adding document!!!", e)
//                }
//            //데이터 추가 insert to db
            
            
            //데이터 추가2 insert to db
//            //Create a new user with a first, middle, and last name
//            val user = hashMapOf(
//                "first" to "Alan",
//                "middle" to "Mathison",
//                "last" to "Turing",
//                "born" to 1912
//            )
//
//            //Add a new document with a generated ID
//            db.collection("users").add(user)
//                .addOnSuccessListener {
//                    documentReference -> Log.d(TAG, "DocumentSnapshop added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener {
//                    e -> Log.w(TAG, "Error adding document!!!", e)
//                }
//            //데이터 추가2 insert to db


//            //데이터 읽기 select
//            db.collection("users")
//                .get()
//                .addOnSuccessListener {
//                    result ->
//                    for (document in result){
//                        Log.d(TAG, "#{document.id} => ${document.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    ex ->
//                    Log.w(TAG, "Error getting documents!!!!!!!", ex)
//                }
//            //데이터 읽기 select

//            // (users)컬렉션 내부의 alovelace 문서를 가리키는 참조 -- 개별 문서를 '읽기' '쓰기' 액션.
//            val alovelaceDocumentRef = db.collection("users").document("alovelace")
//
//            //컬렉션을 가리키는 참조 -- 컬렉션의 '문서를 쿼리'한다.
//            val usersCollectionRef = db.collection("users")
//
//            //컬렉션은 기본적으로 하나만 가져온다. 하위컬렉션을 전체로 가져오려면, 컬렉션 그룹쿼리를 사용하면 된다.
//            // 컬렉션 그룹쿼리는, 컬렉션 아이디가 동일한 하위 컬렉션 전체를 쿼리한다.
//
//            val messageRef = db
//                .collection("rooms").document("roomA")
//                .collection("messages").document("message1")
//            //위에 컬렉션과 문서가 '교대로' 나타나는 패턴에 주목.
//            // 컬렉션과 문서는 항상 이 패턴을 따라가야 한다. 컬렉션에 속한 컬렉션이나, 문서에 속한 문서는 참조할 수 없다.
//
//            // 하위 컬렉션을 사용해서 데이터를 계층적으로 구조화하면 데이터에 쉽게 액세스 가능.
//            // 즉, roomA의 모든 메세지를 가져오려면  하위 컬렉션인 messages에 대한 컬렉션 참조를 만들고, 다른 클렉션 참조와 같은 방식으로 상호작용 하면 된다.
//            //하위 컬렉션 문서도 하위 컬렉션을 포함할 수 있다. --> 중첩이 가능하다. 중첩의 최대 깊이는 100개.
        }


        btnLogin.setOnClickListener {
            val intentLoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentLoginActivity)
        }

        btnRegisterView.setOnClickListener {
            val intentRegisterActivity = Intent(this, RegisterActivity::class.java);
            startActivity(intentRegisterActivity);
        }
    }



}