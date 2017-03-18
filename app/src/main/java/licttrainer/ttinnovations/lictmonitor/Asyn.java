package licttrainer.ttinnovations.lictmonitor;
import android.os.AsyncTask;
public class Asyn extends AsyncTask<String,Void,Integer>
{

   /* @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }*/

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
/* @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }*/

    @Override
    protected Integer doInBackground(String... params) {
        return 0;
    }
/*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }*/


    @Override
    protected void onPostExecute(Integer  o) {

        super.onPostExecute(o);
    }


}
