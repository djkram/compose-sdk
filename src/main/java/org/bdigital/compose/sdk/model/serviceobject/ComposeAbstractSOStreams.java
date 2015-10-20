package org.bdigital.compose.sdk.model.serviceobject;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractSOStreams extends HashMap<String, ComposeAbstractSOStream> {

    private static final long serialVersionUID = -872145118469995829L;

}
