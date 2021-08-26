package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EitherModelOrReferenceObjectTest {
    public static class Outer {
        EitherModelOrReferenceObject<ContactObject> contactOrRef;

        public EitherModelOrReferenceObject<ContactObject> getContactOrRef() {
            return contactOrRef;
        }

        public void setContactOrRef(EitherModelOrReferenceObject<ContactObject> contactOrRef) {
            this.contactOrRef = contactOrRef;
        }
    }
    private Outer modelOuter;
    private Outer referenceOuter;

    @BeforeEach
    void setup() {
        ContactObject contact = new ContactObject();
        contact.setName("george zhou");
        contact.setEmail("George.Zhou@globalbanking.com");
        contact.setUrl("https://www.globalbanking.com/profiles/george-zhou");
        EitherModelOrReferenceObject<ContactObject> contactWrapper = EitherModelOrReferenceObject.modelObject(contact);
        modelOuter = new Outer();
        modelOuter.setContactOrRef(contactWrapper);

        ReferenceObject reference = new ReferenceObject();
        reference.setRef("components/#/schemas/george-zhou");
        EitherModelOrReferenceObject<ContactObject> referenceWrapper = EitherModelOrReferenceObject.referenceObject(reference);
        referenceOuter = new Outer();
        referenceOuter.setContactOrRef(referenceWrapper);
    }

    @Test
    void roundtripModelObject() throws Exception {
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(modelOuter);
        System.out.println(json);
        Outer back = om.readValue(json, Outer.class);
        assertEquals(modelOuter.getContactOrRef(), back.getContactOrRef());
    }

    @Test
    void roundtripReferenceObject() throws Exception {
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(referenceOuter);
        System.out.println(json);
        Outer back = om.readValue(json, Outer.class);
        assertEquals(referenceOuter.getContactOrRef(), back.getContactOrRef());
    }
}