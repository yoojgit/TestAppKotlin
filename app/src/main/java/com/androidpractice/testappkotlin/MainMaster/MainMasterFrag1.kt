package com.androidpractice.testappkotlin.MainMaster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androidpractice.testappkotlin.R

class MainMasterFrag1:Fragment(){

    //inflate = xml 에 표기된 레이아웃들을 메모리에 객체화 하는 action. 즉, xml 코드 객체화하여 코드에서 사용하기 위함.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //setContentView 똑같은거  == xml 객체화시키는 inflate 동작. 이거로 ui 요소들 액세스 가능.
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view //super.onCreateView(inflater, container, savedInstanceState)
    }

}