package com.prot.apitool.openapi.spec.model.v30.sedes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

class SeDesUtils {
    public static void serialize(Object obj, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (obj == null) {
            return;

        } else if (obj instanceof String) {             // TextNode
            gen.writeString((String)obj);

        } else if (obj instanceof Short) {          // ShortNode
            gen.writeNumber((Short)obj);
        } else if (obj instanceof Integer) {          // IntNode
            gen.writeNumber((Integer)obj);
        } else if (obj instanceof Long) {              // LongNode
            gen.writeNumber((Long)obj);
        } else if (obj instanceof Byte) {               // no corresponding node
            gen.writeNumber((Byte)obj);
        } else if (obj instanceof Float) {              // FloatNode
            gen.writeNumber((Float)obj);
        } else if (obj instanceof Double) {              // DoubleNode
            gen.writeNumber((Double)obj);
        } else if (obj instanceof BigInteger) {         // BigIntegerNode
            gen.writeNumber((BigInteger)obj);
        } else if (obj instanceof BigDecimal) {         // DecimalNode
            gen.writeNumber((BigDecimal)obj);

        } else if (obj instanceof Boolean) {             // BooleanNode
            gen.writeBoolean((Boolean)obj);
        } else {
            JsonNode node = ((ObjectMapper) gen.getCodec()).valueToTree(obj);
            if (node instanceof ObjectNode) {
                gen.writeTree(node);

            } else if (node instanceof TextNode) {             // TextNode
                gen.writeString(((TextNode)node).textValue());

            } else if (node instanceof ShortNode) {          // ShortNode
                gen.writeNumber(((ShortNode)node).shortValue());
            } else if (node instanceof IntNode) {          // IntNode
                gen.writeNumber(((IntNode)node).intValue());
            } else if (node instanceof LongNode) {              // LongNode
                gen.writeNumber(((LongNode)node).longValue());
            } else if (node instanceof FloatNode) {              // FloatNode
                gen.writeNumber(((FloatNode)node).floatValue());
            } else if (node instanceof DoubleNode) {              // DoubleNode
                gen.writeNumber(((DoubleNode)node).doubleValue());
            } else if (node instanceof BigIntegerNode) {         // BigIntegerNode
                gen.writeNumber(((BigIntegerNode)node).bigIntegerValue());
            } else if (node instanceof DecimalNode) {         // DecimalNode
                gen.writeNumber(((DecimalNode)node).decimalValue());

            } else if (node instanceof BooleanNode) {             // BooleanNode
                gen.writeBoolean(((BooleanNode)obj).asBoolean());

            } else {
                // BinaryNode, MissingNode, NullNode, POJONode
                throw new UnsupportedOperationException("No support json node type: " + node.getClass().getName());
            }
        }
    }
}
