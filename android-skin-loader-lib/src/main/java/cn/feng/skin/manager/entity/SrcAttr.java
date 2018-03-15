package cn.feng.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import cn.feng.skin.manager.loader.SkinManager;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/27$ 14:10$
 * <p/>
 */
public class SrcAttr extends SkinAttr{
    @Override
    public void apply(View view) {
        if(view instanceof ImageView){
            ImageView imageView = (ImageView) view;
            if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
                imageView.setImageResource(SkinManager.getInstance().getColor(attrValueRefId));
            }else if(RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)){
                Drawable bg = SkinManager.getInstance().getDrawable(attrValueRefId);
                imageView.setImageDrawable(bg);
            }
        }
    }
}
