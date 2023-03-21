## Set  Up Project

run `/gradle ` 

```java

class Main{
    public void logic(){
    }
        System.out.println(sentence_to_client);

        String[]data=sentence_from_client.split(" ");

        Action action=filterAction(data[0]);

        if(action==null){
        sentence_to_client="Invalid action";
        outToClient.writeBytes(sentence_to_client);
        continue;
        }
        process(action,service,data[1]);
        }
}

```