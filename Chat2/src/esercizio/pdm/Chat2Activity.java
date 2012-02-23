package esercizio.pdm;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterStorage;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import android.R.string;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Chat2Activity extends Activity {
    /** Called when the activity is first created. */
   
    
   EditText et;
   ListView lv;
   Connection connection;
   String username;
   String pass;
   String utente;
   ArrayAdapter<String> adapter;
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        et=(EditText)findViewById(R.id.EditText1);
        lv=(ListView)findViewById(R.id.listView1);
        username=getIntent().getExtras().getString("Username");
        pass=getIntent().getExtras().getString("Password");
        utente=getIntent().getExtras().getString("Utente");
        adapter=new ArrayAdapter<String>(Chat2Activity.this, R.layout.row, R.id.rowtext1);
        lv.setAdapter(adapter);
        lv.setSelection(adapter.getCount()-1);
        
        Button btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				adapter.add("ME:"+et.getText().toString()+"\n" );
				Message msg=new Message();
				msg.setTo(utente+"@ppl.eln.uniroma2.it");
				msg.setBody(et.getText().toString());
				connection.sendPacket(msg);
			}
		});  
        
        try {
        	ConnectionConfiguration config=new ConnectionConfiguration("ppl.eln.uniroma2.it" , 5222);
        	config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        	connection=new XMPPConnection(config);
        	connection.connect();
        	connection.login(username, pass);
        } catch (XMPPException e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        connection.addPacketListener(new PacketListener() {
			
			@Override
			public void processPacket(Packet pkt) {
				// TODO Auto-generated method stub
				Message msg=(Message) pkt;
				String from=msg.getFrom();
				String body=msg.getBody();
				adapter.add(from+":"+body+"\n");
			}
		}, new MessageTypeFilter(Message.Type.normal));
    }
}