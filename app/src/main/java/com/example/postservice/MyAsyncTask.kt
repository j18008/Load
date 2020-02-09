package com.example.postservice

import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import kotlin.properties.Delegates


class MyAsyncTask : AsyncTask<String, Int, Long>, SearchManager.OnCancelListener, DialogInterface.OnCancelListener{
    var context: Context by Delegates.notNull<Context>()
    constructor(context: Context){
        this.context = context
    }
    val TAG: String = "MyAsyncTask"
    var dialog: ProgressDialog by Delegates.notNull<ProgressDialog>()

    override fun onPreExecute() {
        Log.d(TAG, "onPreExecute")
        dialog = ProgressDialog(context)
        dialog.setTitle("しばらくお待ちください")
        dialog.setMessage("～ロード中～")
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog.setCancelable(true)
        dialog.max = 100
        dialog.setProgress(0)
        dialog.show()
    }

    override fun doInBackground(vararg params: String?): Long {
        Log.d(TAG, "doInBackground - " + params[0])

        try{
            for(i in 0..9) {
                if(isCancelled){
                    Log.d(TAG, "Cancelled!")
                    break
                }

                Thread.sleep(1000)
                publishProgress((i + 1) * 10)
            }

        } catch (e: InterruptedException){
            Log.d(TAG, "InterruptedException in doInBackground")
        }

        return 123L
    }

    override fun onCancel() {

    }

    override fun onCancel(p0: DialogInterface?) {
        Log.d(TAG, "Dialog onCancel...calling cancel(true)")
        this.cancel(true)
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        Log.d(TAG, "onProgressUpdate - "  + values[0])
        var value:Int = values[0]!!.toInt()
        dialog.setProgress(value)
    }


    override fun onPostExecute(result: Long?) {
        Log.d(TAG, "onPostExecute - " + result)
        dialog.dismiss()
    }
}