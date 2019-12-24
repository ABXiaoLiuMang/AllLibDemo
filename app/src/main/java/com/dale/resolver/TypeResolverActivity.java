package com.dale.resolver;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.resolver.interfaces.Bar;
import com.dale.resolver.interfaces.Foo;
import com.dale.utils.LogUtils;
import com.dale.utils.ToastUtils;

import net.jodah.typetools.TypeResolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeResolverActivity extends ABBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_type_resolver;
    }

//    @Override
//    protected void initPresenters() {
//        Class<?>[] typeArgs = TypeResolver.resolveRawArguments(Foo.class, Bar.class);
//        LogUtils.d("arg1:" + typeArgs[0].getSimpleName() + " arg2:" + typeArgs[1].getSimpleName());
//
//
////        typeArgs = TypeResolver.reify(Foo.class, Bar.class);
////
////        ParameterizedType paramType = (ParameterizedType) typeArgs;
////        Type[] actualTypeArgs = paramType.getActualTypeArguments();
////        ParameterizedType arg = (ParameterizedType)actualTypeArgs[0];
//    }

    @Override
    protected void initViewsAndEvents() {

    }
}
