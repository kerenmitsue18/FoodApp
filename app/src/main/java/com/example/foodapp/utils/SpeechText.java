package com.example.foodapp.utils;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.speech.RecognizerIntent;

public class SpeechText {


    private final int RECOGNIZER_CODE = 1;

    public Intent createSpeechRecognizerIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora");
        return intent;
    }

    public void processSpeechResults(int requestCode, int resultCode, Intent data, SpeechRecognitionCallback callback) {
        if (requestCode == RECOGNIZER_CODE && resultCode == RESULT_OK) {
            callback.onRecognitionResult(data);
        }
    }

    public static String capitalizarPrimeraLetra(String palabra) {
        return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1);
    }

    public int getRECOGNIZER_CODE() {
        return RECOGNIZER_CODE;
    }

    public interface SpeechRecognitionCallback {
        void onRecognitionResult(Intent data);
    }
}
