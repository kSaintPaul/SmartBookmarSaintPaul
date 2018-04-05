package smartbookmarks.kevinsaintpaul.diiage.org.smartbookmarksksaintpaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void MyCommentary_OnClick(View view) {
        Intent intent = new Intent(this, CommentsActivity.class);

        Bundle bundle = new Bundle();
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void AddCommentary_OnClick(View view) {
        Intent intent = new Intent(this, AddCommentActivity.class);

        Bundle bundle = new Bundle();
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
