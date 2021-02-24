package com.example.core.interfaces;

import androidx.fragment.app.Fragment;

import com.example.core.QuestionData;

public interface QFragment {
    Fragment getFragment(QuestionData questionData, callbackInterface listener);
}
