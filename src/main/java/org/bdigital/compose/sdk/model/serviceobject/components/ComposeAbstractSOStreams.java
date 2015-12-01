package org.bdigital.compose.sdk.model.serviceobject.components;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * "streams:{}" component for ServiceObject JSON Model
 * 
 *        "location": {
 *           "channels": {
 *               "latitude": {
 *                   "type": "number",
 *                   "unit": "degrees"
 *               },
 *               "longitude": {
 *                   "type": "number",
 *                   "unit": "degrees"
 *               }
 *           },
 *           "description": "GPS outdoor location",
 *           "type": "sensor"
 *       }
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractSOStreams extends HashMap<String, ComposeAbstractSOStream> {

    private static final long serialVersionUID = -872145118469995829L;

}
