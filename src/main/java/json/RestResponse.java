package json;

import java.util.ArrayList;

public class RestResponse {

        private ArrayList<Result> result;

        private String[] messages;

        public ArrayList<Result> getResult ()
        {
            return result;
        }

        public void setResult (ArrayList<Result> result)
        {
            this.result = result;
        }

        public String[] getMessages ()
        {
            return messages;
        }

        public void setMessages (String[] messages)
        {
            this.messages = messages;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [result = "+result+", messages = "+messages+"]";
        }
    }