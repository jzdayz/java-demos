package io.github.jzdayz.protostuff;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) {
        final Schema<TestBean> schema = RuntimeSchema.getSchema(TestBean.class);
        TestBean tb = new TestBean();
        tb.setName("小白");
        tb.setAge(19);

        //protostuff
        LinkedBuffer buffer = LinkedBuffer.allocate(512);
        final byte[] bytes = ProtostuffIOUtil.toByteArray(tb, schema, buffer);
        buffer.clear();
        // json
        System.out.println(bytes.length);
        System.out.println(JSON.toJSONBytes(tb).length);
        //protostuff
        final TestBean testBean = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, testBean, schema);
        System.out.println(testBean);
        //hessian
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(ba);
        try {
            hessian2Output.writeObject(testBean);
            hessian2Output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hessian=>" + ba.toByteArray().length);

        ByteArrayInputStream in = new ByteArrayInputStream(ba.toByteArray());
        Hessian2Input hessian2Input = new Hessian2Input(in);
        try {
            final Object o = hessian2Input.readObject(TestBean.class);
            System.out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("DONE!!");


    }
}
