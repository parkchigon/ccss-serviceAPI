
/**
 * RetrieveUseSvcListServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */
package lguplus.u3.webservice.sb638;

/*
*  RetrieveUseSvcListServiceStub java implementation
*/
@SuppressWarnings({"rawtypes", "serial", "deprecation", "unchecked", "unused"})
public class RetrieveUseSvcListServiceStub extends org.apache.axis2.client.Stub {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private static int counter = 0;

	private static synchronized java.lang.String getUniqueSuffix() {
		// reset the counter if it is greater than 99999
		if (counter > 99999) {
			counter = 0;
		}
		counter = counter + 1;
		return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
	}

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService("RetrieveUseSvcListService" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[1];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://lguplus/u3/esb", "retrieveUseSvcList"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

	}

	// populates the faults
	private void populateFaults() {

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public RetrieveUseSvcListServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public RetrieveUseSvcListServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener) throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

		_serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

	}

	/**
	 * Default Constructor
	 */
	public RetrieveUseSvcListServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://172.22.14.79:15011/CSSI/SB/RetrieveUseSvcList");

	}

	/**
	 * Default Constructor
	 */
	public RetrieveUseSvcListServiceStub() throws org.apache.axis2.AxisFault {

		this("http://172.22.14.79:15011/CSSI/SB/RetrieveUseSvcList");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public RetrieveUseSvcListServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see lguplus.u3.webservice.sb638.RetrieveUseSvcListService#retrieveUseSvcList
	 * @param retrieveUseSvcList
	 * 
	 */

	public lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse retrieveUseSvcList(

			lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcList retrieveUseSvcList)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient.getOptions()
					.setAction("http://lguplus/u3/esb/RetrieveUseSvcListPortType/RetrieveUseSvcListRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
			_operationClient.getOptions().setTimeOutInMilliSeconds(10000);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), retrieveUseSvcList,
					optimizeContent(new javax.xml.namespace.QName("http://lguplus/u3/esb", "retrieveUseSvcList")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// http://172.22.14.79:15011/CSSI/SB/RetrieveUseSvcList
	public static class ESBHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = ESBHeader
		 * Namespace URI = java:lguplus.u3.esb.common Namespace Prefix = ns2
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.common")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ServiceID
		 */

		protected java.lang.String localServiceID;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getServiceID() {
			return localServiceID;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ServiceID
		 */
		public void setServiceID(java.lang.String param) {

			this.localServiceID = param;

		}

		/**
		 * field for TransactionID
		 */

		protected java.lang.String localTransactionID;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getTransactionID() {
			return localTransactionID;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            TransactionID
		 */
		public void setTransactionID(java.lang.String param) {

			this.localTransactionID = param;

		}

		/**
		 * field for SystemID
		 */

		protected java.lang.String localSystemID;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSystemID() {
			return localSystemID;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SystemID
		 */
		public void setSystemID(java.lang.String param) {

			this.localSystemID = param;

		}

		/**
		 * field for ErrCode
		 */

		protected java.lang.String localErrCode;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getErrCode() {
			return localErrCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ErrCode
		 */
		public void setErrCode(java.lang.String param) {

			this.localErrCode = param;

		}

		/**
		 * field for ErrMsg
		 */

		protected java.lang.String localErrMsg;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getErrMsg() {
			return localErrMsg;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ErrMsg
		 */
		public void setErrMsg(java.lang.String param) {

			this.localErrMsg = param;

		}

		/**
		 * field for Reserved
		 */

		protected java.lang.String localReserved;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getReserved() {
			return localReserved;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Reserved
		 */
		public void setReserved(java.lang.String param) {

			this.localReserved = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ESBHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.common");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ESBHeader", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ESBHeader", xmlWriter);
				}

			}

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ServiceID", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ServiceID");
				}

			} else {
				xmlWriter.writeStartElement("ServiceID");
			}

			if (localServiceID == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("ServiceID cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localServiceID);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "TransactionID", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "TransactionID");
				}

			} else {
				xmlWriter.writeStartElement("TransactionID");
			}

			if (localTransactionID == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("TransactionID cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localTransactionID);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "SystemID", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "SystemID");
				}

			} else {
				xmlWriter.writeStartElement("SystemID");
			}

			if (localSystemID == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("SystemID cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localSystemID);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ErrCode", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ErrCode");
				}

			} else {
				xmlWriter.writeStartElement("ErrCode");
			}

			if (localErrCode == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localErrCode);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ErrMsg", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ErrMsg");
				}

			} else {
				xmlWriter.writeStartElement("ErrMsg");
			}

			if (localErrMsg == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localErrMsg);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "Reserved", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "Reserved");
				}

			} else {
				xmlWriter.writeStartElement("Reserved");
			}

			if (localReserved == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localReserved);

			}

			xmlWriter.writeEndElement();

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ServiceID"));

			if (localServiceID != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localServiceID));
			} else {
				throw new org.apache.axis2.databinding.ADBException("ServiceID cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "TransactionID"));

			if (localTransactionID != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransactionID));
			} else {
				throw new org.apache.axis2.databinding.ADBException("TransactionID cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "SystemID"));

			if (localSystemID != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSystemID));
			} else {
				throw new org.apache.axis2.databinding.ADBException("SystemID cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrCode"));

			elementList.add(localErrCode == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localErrCode));

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrMsg"));

			elementList.add(localErrMsg == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localErrMsg));

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "Reserved"));

			elementList.add(localReserved == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserved));

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static ESBHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				ESBHeader object = new ESBHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ESBHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ESBHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ServiceID")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setServiceID(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "TransactionID")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTransactionID(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "SystemID")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSystemID(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrCode")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setErrCode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrMsg")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setErrMsg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "Reserved")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setReserved(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsInputInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = DsInputInVO
		 * Namespace URI = java:lguplus.u3.esb.sb638 Namespace Prefix = ns88
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sb638")) {
				return "ns88";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for SSearchCb
		 */

		protected java.lang.String localSSearchCb;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSSearchCb() {
			return localSSearchCb;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SSearchCb
		 */
		public void setSSearchCb(java.lang.String param) {

			this.localSSearchCb = param;

		}

		/**
		 * field for SSrchValue
		 */

		protected java.lang.String localSSrchValue;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSSrchValue() {
			return localSSrchValue;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SSrchValue
		 */
		public void setSSrchValue(java.lang.String param) {

			this.localSSrchValue = param;

		}

		/**
		 * field for NextOperatorId
		 */

		protected java.lang.String localNextOperatorId;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getNextOperatorId() {
			return localNextOperatorId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            NextOperatorId
		 */
		public void setNextOperatorId(java.lang.String param) {

			this.localNextOperatorId = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					DsInputInVO.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sb638");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsInputInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsInputInVO",
							xmlWriter);
				}

			}

			namespace = "java:lguplus.u3.esb.sb638";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "sSearchCb", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "sSearchCb");
				}

			} else {
				xmlWriter.writeStartElement("sSearchCb");
			}

			if (localSSearchCb == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("sSearchCb cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localSSearchCb);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.sb638";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "sSrchValue", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "sSrchValue");
				}

			} else {
				xmlWriter.writeStartElement("sSrchValue");
			}

			if (localSSrchValue == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("sSrchValue cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localSSrchValue);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.sb638";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "nextOperatorId", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "nextOperatorId");
				}

			} else {
				xmlWriter.writeStartElement("nextOperatorId");
			}

			if (localNextOperatorId == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("nextOperatorId cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localNextOperatorId);

			}

			xmlWriter.writeEndElement();

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sSearchCb"));

			if (localSSearchCb != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSSearchCb));
			} else {
				throw new org.apache.axis2.databinding.ADBException("sSearchCb cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sSrchValue"));

			if (localSSrchValue != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSSrchValue));
			} else {
				throw new org.apache.axis2.databinding.ADBException("sSrchValue cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "nextOperatorId"));

			if (localNextOperatorId != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNextOperatorId));
			} else {
				throw new org.apache.axis2.databinding.ADBException("nextOperatorId cannot be null!!");
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static DsInputInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsInputInVO object = new DsInputInVO();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"DsInputInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsInputInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sSearchCb")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSSearchCb(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sSrchValue")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSSrchValue(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ExtensionMapper {

		public static java.lang.Object getTypeObject(java.lang.String namespaceURI, java.lang.String typeName,
				javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {

			if ("java:lguplus.u3.esb.common".equals(namespaceURI) && "ESBHeader".equals(typeName)) {

				return ESBHeader.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sb638".equals(namespaceURI) && "DsInputInVO".equals(typeName)) {

				return DsInputInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sb638".equals(namespaceURI) && "RequestBody".equals(typeName)) {

				return RequestBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sb638".equals(namespaceURI) && "ResponseBody".equals(typeName)) {

				return ResponseBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.common".equals(namespaceURI) && "BusinessHeader".equals(typeName)) {

				return BusinessHeader.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sb638".equals(namespaceURI) && "ResponseRecord".equals(typeName)) {

				return ResponseRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sb638".equals(namespaceURI) && "RequestRecord".equals(typeName)) {

				return RequestRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sb638".equals(namespaceURI) && "DsUseSvcListOutVO".equals(typeName)) {

				return DsUseSvcListOutVO.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class BusinessHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * BusinessHeader Namespace URI = java:lguplus.u3.esb.common Namespace Prefix =
		 * ns2
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.common")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ResultCode
		 */

		protected java.lang.String localResultCode;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultCode() {
			return localResultCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultCode
		 */
		public void setResultCode(java.lang.String param) {

			this.localResultCode = param;

		}

		/**
		 * field for ResultMessage
		 */

		protected java.lang.String localResultMessage;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultMessage() {
			return localResultMessage;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultMessage
		 */
		public void setResultMessage(java.lang.String param) {

			this.localResultMessage = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					BusinessHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.common");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":BusinessHeader", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "BusinessHeader",
							xmlWriter);
				}

			}

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ResultCode", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ResultCode");
				}

			} else {
				xmlWriter.writeStartElement("ResultCode");
			}

			if (localResultCode == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("ResultCode cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localResultCode);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ResultMessage", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ResultMessage");
				}

			} else {
				xmlWriter.writeStartElement("ResultMessage");
			}

			if (localResultMessage == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localResultMessage);

			}

			xmlWriter.writeEndElement();

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultCode"));

			if (localResultCode != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultCode));
			} else {
				throw new org.apache.axis2.databinding.ADBException("ResultCode cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultMessage"));

			elementList.add(localResultMessage == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultMessage));

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static BusinessHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				BusinessHeader object = new BusinessHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"BusinessHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (BusinessHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultCode")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setResultCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultMessage")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setResultMessage(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ResponseBody implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = ResponseBody
		 * Namespace URI = java:lguplus.u3.esb.sb638 Namespace Prefix = ns88
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sb638")) {
				return "ns88";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DsUseSvcListOutVO This was an Array!
		 */

		protected DsUseSvcListOutVO[] localDsUseSvcListOutVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsUseSvcListOutVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsUseSvcListOutVO[]
		 */
		public DsUseSvcListOutVO[] getDsUseSvcListOutVO() {
			return localDsUseSvcListOutVO;
		}

		/**
		 * validate the array for DsUseSvcListOutVO
		 */
		protected void validateDsUseSvcListOutVO(DsUseSvcListOutVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsUseSvcListOutVO
		 */
		public void setDsUseSvcListOutVO(DsUseSvcListOutVO[] param) {

			validateDsUseSvcListOutVO(param);

			if (param != null) {
				// update the setting tracker
				localDsUseSvcListOutVOTracker = true;
			} else {
				localDsUseSvcListOutVOTracker = false;

			}

			this.localDsUseSvcListOutVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsUseSvcListOutVO
		 */
		public void addDsUseSvcListOutVO(DsUseSvcListOutVO param) {
			if (localDsUseSvcListOutVO == null) {
				localDsUseSvcListOutVO = new DsUseSvcListOutVO[] {};
			}

			// update the setting tracker
			localDsUseSvcListOutVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsUseSvcListOutVO);
			list.add(param);
			this.localDsUseSvcListOutVO = (DsUseSvcListOutVO[]) list.toArray(new DsUseSvcListOutVO[list.size()]);

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ResponseBody.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sb638");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ResponseBody", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ResponseBody",
							xmlWriter);
				}

			}
			if (localDsUseSvcListOutVOTracker) {
				if (localDsUseSvcListOutVO != null) {
					for (int i = 0; i < localDsUseSvcListOutVO.length; i++) {
						if (localDsUseSvcListOutVO[i] != null) {
							localDsUseSvcListOutVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "DsUseSvcListOutVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsUseSvcListOutVO cannot be null!!");

				}
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localDsUseSvcListOutVOTracker) {
				if (localDsUseSvcListOutVO != null) {
					for (int i = 0; i < localDsUseSvcListOutVO.length; i++) {

						if (localDsUseSvcListOutVO[i] != null) {
							elementList.add(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "DsUseSvcListOutVO"));
							elementList.add(localDsUseSvcListOutVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsUseSvcListOutVO cannot be null!!");

				}

			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static ResponseBody parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				ResponseBody object = new ResponseBody();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ResponseBody".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ResponseBody) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					java.util.ArrayList list1 = new java.util.ArrayList();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "DsUseSvcListOutVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list1.add(DsUseSvcListOutVO.Factory.parse(reader));

						// loop until we find a start element that is not part
						// of this array
						boolean loopDone1 = false;
						while (!loopDone1) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are
								// exiting the xml structure
								loopDone1 = true;
							} else {
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "DsUseSvcListOutVO")
										.equals(reader.getName())) {
									list1.add(DsUseSvcListOutVO.Factory.parse(reader));

								} else {
									loopDone1 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsUseSvcListOutVO(
								(DsUseSvcListOutVO[]) org.apache.axis2.databinding.utils.ConverterUtil
										.convertToArray(DsUseSvcListOutVO.class, list1));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RequestBody implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = RequestBody
		 * Namespace URI = java:lguplus.u3.esb.sb638 Namespace Prefix = ns88
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sb638")) {
				return "ns88";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DsInputInVO
		 */

		protected DsInputInVO localDsInputInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsInputInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsInputInVO
		 */
		public DsInputInVO getDsInputInVO() {
			return localDsInputInVO;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsInputInVO
		 */
		public void setDsInputInVO(DsInputInVO param) {

			if (param != null) {
				// update the setting tracker
				localDsInputInVOTracker = true;
			} else {
				localDsInputInVOTracker = false;

			}

			this.localDsInputInVO = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RequestBody.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sb638");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RequestBody", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RequestBody",
							xmlWriter);
				}

			}
			if (localDsInputInVOTracker) {
				if (localDsInputInVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsInputInVO cannot be null!!");
				}
				localDsInputInVO.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "DsInputInVO"),
						factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localDsInputInVOTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "DsInputInVO"));

				if (localDsInputInVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsInputInVO cannot be null!!");
				}
				elementList.add(localDsInputInVO);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RequestBody parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				RequestBody object = new RequestBody();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RequestBody".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RequestBody) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "DsInputInVO")
									.equals(reader.getName())) {

						object.setDsInputInVO(DsInputInVO.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RetrieveUseSvcListResponse implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"RetrieveUseSvcListResponse", "ns3");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://lguplus/u3/esb")) {
				return "ns3";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ResponseRecord
		 */

		protected ResponseRecord localResponseRecord;

		/**
		 * Auto generated getter method
		 * 
		 * @return ResponseRecord
		 */
		public ResponseRecord getResponseRecord() {
			return localResponseRecord;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResponseRecord
		 */
		public void setResponseRecord(ResponseRecord param) {

			this.localResponseRecord = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RetrieveUseSvcListResponse.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://lguplus/u3/esb");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RetrieveUseSvcListResponse", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"RetrieveUseSvcListResponse", xmlWriter);
				}

			}

			if (localResponseRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseRecord cannot be null!!");
			}
			localResponseRecord.serialize(new javax.xml.namespace.QName("http://lguplus/u3/esb", "ResponseRecord"),
					factory, xmlWriter);

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://lguplus/u3/esb", "ResponseRecord"));

			if (localResponseRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseRecord cannot be null!!");
			}
			elementList.add(localResponseRecord);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RetrieveUseSvcListResponse parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				RetrieveUseSvcListResponse object = new RetrieveUseSvcListResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RetrieveUseSvcListResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RetrieveUseSvcListResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://lguplus/u3/esb", "ResponseRecord")
									.equals(reader.getName())) {

						object.setResponseRecord(ResponseRecord.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ResponseRecord implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * ResponseRecord Namespace URI = java:lguplus.u3.esb.sb638 Namespace Prefix =
		 * ns88
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sb638")) {
				return "ns88";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ESBHeader
		 */

		protected ESBHeader localESBHeader;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localESBHeaderTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return ESBHeader
		 */
		public ESBHeader getESBHeader() {
			return localESBHeader;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ESBHeader
		 */
		public void setESBHeader(ESBHeader param) {

			if (param != null) {
				// update the setting tracker
				localESBHeaderTracker = true;
			} else {
				localESBHeaderTracker = false;

			}

			this.localESBHeader = param;

		}

		/**
		 * field for BusinessHeader
		 */

		protected BusinessHeader localBusinessHeader;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBusinessHeaderTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return BusinessHeader
		 */
		public BusinessHeader getBusinessHeader() {
			return localBusinessHeader;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BusinessHeader
		 */
		public void setBusinessHeader(BusinessHeader param) {

			if (param != null) {
				// update the setting tracker
				localBusinessHeaderTracker = true;
			} else {
				localBusinessHeaderTracker = false;

			}

			this.localBusinessHeader = param;

		}

		/**
		 * field for ResponseBody
		 */

		protected ResponseBody localResponseBody;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localResponseBodyTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return ResponseBody
		 */
		public ResponseBody getResponseBody() {
			return localResponseBody;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResponseBody
		 */
		public void setResponseBody(ResponseBody param) {

			if (param != null) {
				// update the setting tracker
				localResponseBodyTracker = true;
			} else {
				localResponseBodyTracker = false;

			}

			this.localResponseBody = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ResponseRecord.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sb638");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ResponseRecord", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ResponseRecord",
							xmlWriter);
				}

			}
			if (localESBHeaderTracker) {
				if (localESBHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
				}
				localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ESBHeader"),
						factory, xmlWriter);
			}
			if (localBusinessHeaderTracker) {
				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				localBusinessHeader.serialize(
						new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "BusinessHeader"), factory,
						xmlWriter);
			}
			if (localResponseBodyTracker) {
				if (localResponseBody == null) {
					throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
				}
				localResponseBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ResponseBody"),
						factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localESBHeaderTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ESBHeader"));

				if (localESBHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
				}
				elementList.add(localESBHeader);
			}
			if (localBusinessHeaderTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "BusinessHeader"));

				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				elementList.add(localBusinessHeader);
			}
			if (localResponseBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ResponseBody"));

				if (localResponseBody == null) {
					throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
				}
				elementList.add(localResponseBody);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static ResponseRecord parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				ResponseRecord object = new ResponseRecord();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ResponseRecord".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ResponseRecord) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ESBHeader")
									.equals(reader.getName())) {

						object.setESBHeader(ESBHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "BusinessHeader")
									.equals(reader.getName())) {

						object.setBusinessHeader(BusinessHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ResponseBody")
									.equals(reader.getName())) {

						object.setResponseBody(ResponseBody.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RequestRecord implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * RequestRecord Namespace URI = java:lguplus.u3.esb.sb638 Namespace Prefix =
		 * ns88
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sb638")) {
				return "ns88";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ESBHeader
		 */

		protected ESBHeader localESBHeader;

		/**
		 * Auto generated getter method
		 * 
		 * @return ESBHeader
		 */
		public ESBHeader getESBHeader() {
			return localESBHeader;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ESBHeader
		 */
		public void setESBHeader(ESBHeader param) {

			this.localESBHeader = param;

		}

		/**
		 * field for RequestBody
		 */

		protected RequestBody localRequestBody;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRequestBodyTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return RequestBody
		 */
		public RequestBody getRequestBody() {
			return localRequestBody;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestBody
		 */
		public void setRequestBody(RequestBody param) {

			if (param != null) {
				// update the setting tracker
				localRequestBodyTracker = true;
			} else {
				localRequestBodyTracker = false;

			}

			this.localRequestBody = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RequestRecord.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sb638");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RequestRecord", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RequestRecord",
							xmlWriter);
				}

			}

			if (localESBHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
			}
			localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ESBHeader"), factory,
					xmlWriter);
			if (localRequestBodyTracker) {
				if (localRequestBody == null) {
					throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
				}
				localRequestBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "RequestBody"),
						factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ESBHeader"));

			if (localESBHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
			}
			elementList.add(localESBHeader);
			if (localRequestBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "RequestBody"));

				if (localRequestBody == null) {
					throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
				}
				elementList.add(localRequestBody);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RequestRecord parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				RequestRecord object = new RequestRecord();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RequestRecord".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RequestRecord) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ESBHeader")
									.equals(reader.getName())) {

						object.setESBHeader(ESBHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "RequestBody")
									.equals(reader.getName())) {

						object.setRequestBody(RequestBody.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RetrieveUseSvcList implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"RetrieveUseSvcList", "ns3");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://lguplus/u3/esb")) {
				return "ns3";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for RequestRecord
		 */

		protected RequestRecord localRequestRecord;

		/**
		 * Auto generated getter method
		 * 
		 * @return RequestRecord
		 */
		public RequestRecord getRequestRecord() {
			return localRequestRecord;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestRecord
		 */
		public void setRequestRecord(RequestRecord param) {

			this.localRequestRecord = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RetrieveUseSvcList.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://lguplus/u3/esb");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RetrieveUseSvcList", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RetrieveUseSvcList",
							xmlWriter);
				}

			}

			if (localRequestRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestRecord cannot be null!!");
			}
			localRequestRecord.serialize(new javax.xml.namespace.QName("http://lguplus/u3/esb", "RequestRecord"),
					factory, xmlWriter);

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://lguplus/u3/esb", "RequestRecord"));

			if (localRequestRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestRecord cannot be null!!");
			}
			elementList.add(localRequestRecord);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RetrieveUseSvcList parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				RetrieveUseSvcList object = new RetrieveUseSvcList();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RetrieveUseSvcList".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RetrieveUseSvcList) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://lguplus/u3/esb", "RequestRecord")
									.equals(reader.getName())) {

						object.setRequestRecord(RequestRecord.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsUseSvcListOutVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsUseSvcListOutVO Namespace URI = java:lguplus.u3.esb.sb638 Namespace Prefix
		 * = ns88
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sb638")) {
				return "ns88";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for EntrNo
		 */

		protected java.lang.String localEntrNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrNo() {
			return localEntrNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrNo
		 */
		public void setEntrNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrNoTracker = true;
			} else {
				localEntrNoTracker = true;

			}

			this.localEntrNo = param;

		}

		/**
		 * field for EntrSttsNm
		 */

		protected java.lang.String localEntrSttsNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsNm() {
			return localEntrSttsNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsNm
		 */
		public void setEntrSttsNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsNmTracker = true;
			} else {
				localEntrSttsNmTracker = true;

			}

			this.localEntrSttsNm = param;

		}

		/**
		 * field for ProdNo
		 */

		protected java.lang.String localProdNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdNo() {
			return localProdNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdNo
		 */
		public void setProdNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdNoTracker = true;
			} else {
				localProdNoTracker = true;

			}

			this.localProdNo = param;

		}

		/**
		 * field for Aceno
		 */

		protected java.lang.String localAceno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAcenoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAceno() {
			return localAceno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Aceno
		 */
		public void setAceno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcenoTracker = true;
			} else {
				localAcenoTracker = true;

			}

			this.localAceno = param;

		}

		/**
		 * field for ProdCd
		 */

		protected java.lang.String localProdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdCd() {
			return localProdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdCd
		 */
		public void setProdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdCdTracker = true;
			} else {
				localProdCdTracker = true;

			}

			this.localProdCd = param;

		}

		/**
		 * field for BillAcntNo
		 */

		protected java.lang.String localBillAcntNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntNo() {
			return localBillAcntNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntNo
		 */
		public void setBillAcntNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntNoTracker = true;
			} else {
				localBillAcntNoTracker = true;

			}

			this.localBillAcntNo = param;

		}

		/**
		 * field for HldrCustNo
		 */

		protected java.lang.String localHldrCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localHldrCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getHldrCustNo() {
			return localHldrCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            HldrCustNo
		 */
		public void setHldrCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHldrCustNoTracker = true;
			} else {
				localHldrCustNoTracker = true;

			}

			this.localHldrCustNo = param;

		}

		/**
		 * field for RlusrCustNo
		 */

		protected java.lang.String localRlusrCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustNo() {
			return localRlusrCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustNo
		 */
		public void setRlusrCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustNoTracker = true;
			} else {
				localRlusrCustNoTracker = true;

			}

			this.localRlusrCustNo = param;

		}

		/**
		 * field for SvcgrpCd
		 */

		protected java.lang.String localSvcgrpCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcgrpCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcgrpCd() {
			return localSvcgrpCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcgrpCd
		 */
		public void setSvcgrpCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcgrpCdTracker = true;
			} else {
				localSvcgrpCdTracker = true;

			}

			this.localSvcgrpCd = param;

		}

		/**
		 * field for SvcKdCd
		 */

		protected java.lang.String localSvcKdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcKdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcKdCd() {
			return localSvcKdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcKdCd
		 */
		public void setSvcKdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcKdCdTracker = true;
			} else {
				localSvcKdCdTracker = true;

			}

			this.localSvcKdCd = param;

		}

		/**
		 * field for SvcCd
		 */

		protected java.lang.String localSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcCd() {
			return localSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcCd
		 */
		public void setSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcCdTracker = true;
			} else {
				localSvcCdTracker = true;

			}

			this.localSvcCd = param;

		}

		/**
		 * field for VtNo
		 */

		protected java.lang.String localVtNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localVtNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getVtNo() {
			return localVtNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            VtNo
		 */
		public void setVtNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localVtNoTracker = true;
			} else {
				localVtNoTracker = true;

			}

			this.localVtNo = param;

		}

		/**
		 * field for LtpymSttsCd
		 */

		protected java.lang.String localLtpymSttsCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtpymSttsCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtpymSttsCd() {
			return localLtpymSttsCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtpymSttsCd
		 */
		public void setLtpymSttsCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtpymSttsCdTracker = true;
			} else {
				localLtpymSttsCdTracker = true;

			}

			this.localLtpymSttsCd = param;

		}

		/**
		 * field for EntrSttsChngDttm
		 */

		protected java.lang.String localEntrSttsChngDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngDttm() {
			return localEntrSttsChngDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngDttm
		 */
		public void setEntrSttsChngDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngDttmTracker = true;
			} else {
				localEntrSttsChngDttmTracker = true;

			}

			this.localEntrSttsChngDttm = param;

		}

		/**
		 * field for FrstEntrDttm
		 */

		protected java.lang.String localFrstEntrDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFrstEntrDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFrstEntrDttm() {
			return localFrstEntrDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FrstEntrDttm
		 */
		public void setFrstEntrDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFrstEntrDttmTracker = true;
			} else {
				localFrstEntrDttmTracker = true;

			}

			this.localFrstEntrDttm = param;

		}

		/**
		 * field for CablPcCont
		 */

		protected java.lang.String localCablPcCont;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCablPcContTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCablPcCont() {
			return localCablPcCont;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CablPcCont
		 */
		public void setCablPcCont(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCablPcContTracker = true;
			} else {
				localCablPcContTracker = true;

			}

			this.localCablPcCont = param;

		}

		/**
		 * field for StopCablPcCont
		 */

		protected java.lang.String localStopCablPcCont;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localStopCablPcContTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getStopCablPcCont() {
			return localStopCablPcCont;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            StopCablPcCont
		 */
		public void setStopCablPcCont(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localStopCablPcContTracker = true;
			} else {
				localStopCablPcContTracker = true;

			}

			this.localStopCablPcCont = param;

		}

		/**
		 * field for WrlsPcCont
		 */

		protected java.lang.String localWrlsPcCont;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localWrlsPcContTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getWrlsPcCont() {
			return localWrlsPcCont;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            WrlsPcCont
		 */
		public void setWrlsPcCont(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localWrlsPcContTracker = true;
			} else {
				localWrlsPcContTracker = true;

			}

			this.localWrlsPcCont = param;

		}

		/**
		 * field for StopWrlsPcCont
		 */

		protected java.lang.String localStopWrlsPcCont;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localStopWrlsPcContTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getStopWrlsPcCont() {
			return localStopWrlsPcCont;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            StopWrlsPcCont
		 */
		public void setStopWrlsPcCont(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localStopWrlsPcContTracker = true;
			} else {
				localStopWrlsPcContTracker = true;

			}

			this.localStopWrlsPcCont = param;

		}

		/**
		 * field for CnvgKdCd
		 */

		protected java.lang.String localCnvgKdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCnvgKdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCnvgKdCd() {
			return localCnvgKdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CnvgKdCd
		 */
		public void setCnvgKdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCnvgKdCdTracker = true;
			} else {
				localCnvgKdCdTracker = true;

			}

			this.localCnvgKdCd = param;

		}

		/**
		 * field for CnvgKdNm
		 */

		protected java.lang.String localCnvgKdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCnvgKdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCnvgKdNm() {
			return localCnvgKdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CnvgKdNm
		 */
		public void setCnvgKdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCnvgKdNmTracker = true;
			} else {
				localCnvgKdNmTracker = true;

			}

			this.localCnvgKdNm = param;

		}

		/**
		 * field for CnvgNo
		 */

		protected java.lang.String localCnvgNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCnvgNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCnvgNo() {
			return localCnvgNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CnvgNo
		 */
		public void setCnvgNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCnvgNoTracker = true;
			} else {
				localCnvgNoTracker = true;

			}

			this.localCnvgNo = param;

		}

		/**
		 * field for SendPhbYn
		 */

		protected java.lang.String localSendPhbYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSendPhbYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSendPhbYn() {
			return localSendPhbYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SendPhbYn
		 */
		public void setSendPhbYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSendPhbYnTracker = true;
			} else {
				localSendPhbYnTracker = true;

			}

			this.localSendPhbYn = param;

		}

		/**
		 * field for IcallPhbYn
		 */

		protected java.lang.String localIcallPhbYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIcallPhbYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIcallPhbYn() {
			return localIcallPhbYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IcallPhbYn
		 */
		public void setIcallPhbYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIcallPhbYnTracker = true;
			} else {
				localIcallPhbYnTracker = true;

			}

			this.localIcallPhbYn = param;

		}

		/**
		 * field for SbgnDlrCd
		 */

		protected java.lang.String localSbgnDlrCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSbgnDlrCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSbgnDlrCd() {
			return localSbgnDlrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SbgnDlrCd
		 */
		public void setSbgnDlrCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSbgnDlrCdTracker = true;
			} else {
				localSbgnDlrCdTracker = true;

			}

			this.localSbgnDlrCd = param;

		}

		/**
		 * field for EntrSttsChngSeqno
		 */

		protected java.lang.String localEntrSttsChngSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngSeqno() {
			return localEntrSttsChngSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngSeqno
		 */
		public void setEntrSttsChngSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngSeqnoTracker = true;
			} else {
				localEntrSttsChngSeqnoTracker = true;

			}

			this.localEntrSttsChngSeqno = param;

		}

		/**
		 * field for RspEntrSttsChngSeqno
		 */

		protected java.lang.String localRspEntrSttsChngSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRspEntrSttsChngSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRspEntrSttsChngSeqno() {
			return localRspEntrSttsChngSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RspEntrSttsChngSeqno
		 */
		public void setRspEntrSttsChngSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRspEntrSttsChngSeqnoTracker = true;
			} else {
				localRspEntrSttsChngSeqnoTracker = true;

			}

			this.localRspEntrSttsChngSeqno = param;

		}

		/**
		 * field for EntrSttsValdStrtDt
		 */

		protected java.lang.String localEntrSttsValdStrtDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsValdStrtDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsValdStrtDt() {
			return localEntrSttsValdStrtDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsValdStrtDt
		 */
		public void setEntrSttsValdStrtDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsValdStrtDtTracker = true;
			} else {
				localEntrSttsValdStrtDtTracker = true;

			}

			this.localEntrSttsValdStrtDt = param;

		}

		/**
		 * field for EntrSttsValdEndDt
		 */

		protected java.lang.String localEntrSttsValdEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsValdEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsValdEndDt() {
			return localEntrSttsValdEndDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsValdEndDt
		 */
		public void setEntrSttsValdEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsValdEndDtTracker = true;
			} else {
				localEntrSttsValdEndDtTracker = true;

			}

			this.localEntrSttsValdEndDt = param;

		}

		/**
		 * field for EntrSttsChngRsnCd
		 */

		protected java.lang.String localEntrSttsChngRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngRsnCd() {
			return localEntrSttsChngRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngRsnCd
		 */
		public void setEntrSttsChngRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngRsnCdTracker = true;
			} else {
				localEntrSttsChngRsnCdTracker = true;

			}

			this.localEntrSttsChngRsnCd = param;

		}

		/**
		 * field for EntrSttsChngRsnDtlCd
		 */

		protected java.lang.String localEntrSttsChngRsnDtlCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngRsnDtlCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngRsnDtlCd() {
			return localEntrSttsChngRsnDtlCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngRsnDtlCd
		 */
		public void setEntrSttsChngRsnDtlCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngRsnDtlCdTracker = true;
			} else {
				localEntrSttsChngRsnDtlCdTracker = true;

			}

			this.localEntrSttsChngRsnDtlCd = param;

		}

		/**
		 * field for RspRsnCd
		 */

		protected java.lang.String localRspRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRspRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRspRsnCd() {
			return localRspRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RspRsnCd
		 */
		public void setRspRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRspRsnCdTracker = true;
			} else {
				localRspRsnCdTracker = true;

			}

			this.localRspRsnCd = param;

		}

		/**
		 * field for EntrSttsChngRsnDtlNm
		 */

		protected java.lang.String localEntrSttsChngRsnDtlNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngRsnDtlNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngRsnDtlNm() {
			return localEntrSttsChngRsnDtlNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngRsnDtlNm
		 */
		public void setEntrSttsChngRsnDtlNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngRsnDtlNmTracker = true;
			} else {
				localEntrSttsChngRsnDtlNmTracker = true;

			}

			this.localEntrSttsChngRsnDtlNm = param;

		}

		/**
		 * field for Memo
		 */

		protected java.lang.String localMemo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localMemoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMemo() {
			return localMemo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Memo
		 */
		public void setMemo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMemoTracker = true;
			} else {
				localMemoTracker = true;

			}

			this.localMemo = param;

		}

		/**
		 * field for ItemId
		 */

		protected java.lang.String localItemId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localItemIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getItemId() {
			return localItemId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ItemId
		 */
		public void setItemId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localItemIdTracker = true;
			} else {
				localItemIdTracker = true;

			}

			this.localItemId = param;

		}

		/**
		 * field for ManfSerialNo
		 */

		protected java.lang.String localManfSerialNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localManfSerialNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getManfSerialNo() {
			return localManfSerialNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ManfSerialNo
		 */
		public void setManfSerialNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localManfSerialNoTracker = true;
			} else {
				localManfSerialNoTracker = true;

			}

			this.localManfSerialNo = param;

		}

		/**
		 * field for ProdNm
		 */

		protected java.lang.String localProdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdNm() {
			return localProdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdNm
		 */
		public void setProdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdNmTracker = true;
			} else {
				localProdNmTracker = true;

			}

			this.localProdNm = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					DsUseSvcListOutVO.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sb638");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsUseSvcListOutVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsUseSvcListOutVO",
							xmlWriter);
				}

			}
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrNo");
					}

				} else {
					xmlWriter.writeStartElement("entrNo");
				}

				if (localEntrNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsNmTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsNm");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsNm");
				}

				if (localEntrSttsNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localProdNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodNo");
					}

				} else {
					xmlWriter.writeStartElement("prodNo");
				}

				if (localProdNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcenoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "aceno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "aceno");
					}

				} else {
					xmlWriter.writeStartElement("aceno");
				}

				if (localAceno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAceno);

				}

				xmlWriter.writeEndElement();
			}
			if (localProdCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodCd");
					}

				} else {
					xmlWriter.writeStartElement("prodCd");
				}

				if (localProdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntNo");
					}

				} else {
					xmlWriter.writeStartElement("billAcntNo");
				}

				if (localBillAcntNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHldrCustNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "hldrCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "hldrCustNo");
					}

				} else {
					xmlWriter.writeStartElement("hldrCustNo");
				}

				if (localHldrCustNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localHldrCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustNo");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustNo");
				}

				if (localRlusrCustNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcgrpCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcgrpCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcgrpCd");
					}

				} else {
					xmlWriter.writeStartElement("svcgrpCd");
				}

				if (localSvcgrpCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcgrpCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcKdCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcKdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcKdCd");
					}

				} else {
					xmlWriter.writeStartElement("svcKdCd");
				}

				if (localSvcKdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcKdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcCd");
					}

				} else {
					xmlWriter.writeStartElement("svcCd");
				}

				if (localSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localVtNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "vtNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "vtNo");
					}

				} else {
					xmlWriter.writeStartElement("vtNo");
				}

				if (localVtNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localVtNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtpymSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltpymSttsCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltpymSttsCd");
					}

				} else {
					xmlWriter.writeStartElement("ltpymSttsCd");
				}

				if (localLtpymSttsCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtpymSttsCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngDttmTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngDttm");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngDttm");
				}

				if (localEntrSttsChngDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localFrstEntrDttmTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "frstEntrDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "frstEntrDttm");
					}

				} else {
					xmlWriter.writeStartElement("frstEntrDttm");
				}

				if (localFrstEntrDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFrstEntrDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localCablPcContTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cablPcCont", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cablPcCont");
					}

				} else {
					xmlWriter.writeStartElement("cablPcCont");
				}

				if (localCablPcCont == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCablPcCont);

				}

				xmlWriter.writeEndElement();
			}
			if (localStopCablPcContTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "stopCablPcCont", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "stopCablPcCont");
					}

				} else {
					xmlWriter.writeStartElement("stopCablPcCont");
				}

				if (localStopCablPcCont == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localStopCablPcCont);

				}

				xmlWriter.writeEndElement();
			}
			if (localWrlsPcContTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "wrlsPcCont", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "wrlsPcCont");
					}

				} else {
					xmlWriter.writeStartElement("wrlsPcCont");
				}

				if (localWrlsPcCont == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localWrlsPcCont);

				}

				xmlWriter.writeEndElement();
			}
			if (localStopWrlsPcContTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "stopWrlsPcCont", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "stopWrlsPcCont");
					}

				} else {
					xmlWriter.writeStartElement("stopWrlsPcCont");
				}

				if (localStopWrlsPcCont == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localStopWrlsPcCont);

				}

				xmlWriter.writeEndElement();
			}
			if (localCnvgKdCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cnvgKdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cnvgKdCd");
					}

				} else {
					xmlWriter.writeStartElement("cnvgKdCd");
				}

				if (localCnvgKdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCnvgKdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localCnvgKdNmTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cnvgKdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cnvgKdNm");
					}

				} else {
					xmlWriter.writeStartElement("cnvgKdNm");
				}

				if (localCnvgKdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCnvgKdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localCnvgNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cnvgNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cnvgNo");
					}

				} else {
					xmlWriter.writeStartElement("cnvgNo");
				}

				if (localCnvgNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCnvgNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localSendPhbYnTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "sendPhbYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "sendPhbYn");
					}

				} else {
					xmlWriter.writeStartElement("sendPhbYn");
				}

				if (localSendPhbYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSendPhbYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localIcallPhbYnTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "icallPhbYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "icallPhbYn");
					}

				} else {
					xmlWriter.writeStartElement("icallPhbYn");
				}

				if (localIcallPhbYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIcallPhbYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localSbgnDlrCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "sbgnDlrCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "sbgnDlrCd");
					}

				} else {
					xmlWriter.writeStartElement("sbgnDlrCd");
				}

				if (localSbgnDlrCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSbgnDlrCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngSeqno");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngSeqno");
				}

				if (localEntrSttsChngSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localRspEntrSttsChngSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rspEntrSttsChngSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rspEntrSttsChngSeqno");
					}

				} else {
					xmlWriter.writeStartElement("rspEntrSttsChngSeqno");
				}

				if (localRspEntrSttsChngSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRspEntrSttsChngSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsValdStrtDtTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsValdStrtDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsValdStrtDt");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsValdStrtDt");
				}

				if (localEntrSttsValdStrtDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsValdStrtDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsValdEndDtTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsValdEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsValdEndDt");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsValdEndDt");
				}

				if (localEntrSttsValdEndDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsValdEndDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngRsnCd");
				}

				if (localEntrSttsChngRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngRsnDtlCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngRsnDtlCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngRsnDtlCd");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngRsnDtlCd");
				}

				if (localEntrSttsChngRsnDtlCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngRsnDtlCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localRspRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rspRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rspRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("rspRsnCd");
				}

				if (localRspRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRspRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngRsnDtlNmTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngRsnDtlNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngRsnDtlNm");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngRsnDtlNm");
				}

				if (localEntrSttsChngRsnDtlNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngRsnDtlNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localMemoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "memo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "memo");
					}

				} else {
					xmlWriter.writeStartElement("memo");
				}

				if (localMemo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localMemo);

				}

				xmlWriter.writeEndElement();
			}
			if (localItemIdTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "itemId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "itemId");
					}

				} else {
					xmlWriter.writeStartElement("itemId");
				}

				if (localItemId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localItemId);

				}

				xmlWriter.writeEndElement();
			}
			if (localManfSerialNoTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "manfSerialNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "manfSerialNo");
					}

				} else {
					xmlWriter.writeStartElement("manfSerialNo");
				}

				if (localManfSerialNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localManfSerialNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localProdNmTracker) {
				namespace = "java:lguplus.u3.esb.sb638";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodNm");
					}

				} else {
					xmlWriter.writeStartElement("prodNm");
				}

				if (localProdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdNm);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localEntrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			if (localEntrSttsNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsNm"));

				elementList.add(localEntrSttsNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsNm));
			}
			if (localProdNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "prodNo"));

				elementList.add(localProdNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNo));
			}
			if (localAcenoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "aceno"));

				elementList.add(localAceno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAceno));
			}
			if (localProdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "prodCd"));

				elementList.add(localProdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdCd));
			}
			if (localBillAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "billAcntNo"));

				elementList.add(localBillAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntNo));
			}
			if (localHldrCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "hldrCustNo"));

				elementList.add(localHldrCustNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHldrCustNo));
			}
			if (localRlusrCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "rlusrCustNo"));

				elementList.add(localRlusrCustNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustNo));
			}
			if (localSvcgrpCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "svcgrpCd"));

				elementList.add(localSvcgrpCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcgrpCd));
			}
			if (localSvcKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "svcKdCd"));

				elementList.add(localSvcKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcKdCd));
			}
			if (localSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "svcCd"));

				elementList.add(localSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcCd));
			}
			if (localVtNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "vtNo"));

				elementList.add(localVtNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVtNo));
			}
			if (localLtpymSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ltpymSttsCd"));

				elementList.add(localLtpymSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLtpymSttsCd));
			}
			if (localEntrSttsChngDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngDttm"));

				elementList.add(localEntrSttsChngDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngDttm));
			}
			if (localFrstEntrDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "frstEntrDttm"));

				elementList.add(localFrstEntrDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFrstEntrDttm));
			}
			if (localCablPcContTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cablPcCont"));

				elementList.add(localCablPcCont == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCablPcCont));
			}
			if (localStopCablPcContTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "stopCablPcCont"));

				elementList.add(localStopCablPcCont == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStopCablPcCont));
			}
			if (localWrlsPcContTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "wrlsPcCont"));

				elementList.add(localWrlsPcCont == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWrlsPcCont));
			}
			if (localStopWrlsPcContTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "stopWrlsPcCont"));

				elementList.add(localStopWrlsPcCont == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStopWrlsPcCont));
			}
			if (localCnvgKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cnvgKdCd"));

				elementList.add(localCnvgKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCnvgKdCd));
			}
			if (localCnvgKdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cnvgKdNm"));

				elementList.add(localCnvgKdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCnvgKdNm));
			}
			if (localCnvgNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cnvgNo"));

				elementList.add(localCnvgNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCnvgNo));
			}
			if (localSendPhbYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sendPhbYn"));

				elementList.add(localSendPhbYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSendPhbYn));
			}
			if (localIcallPhbYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "icallPhbYn"));

				elementList.add(localIcallPhbYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIcallPhbYn));
			}
			if (localSbgnDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sbgnDlrCd"));

				elementList.add(localSbgnDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSbgnDlrCd));
			}
			if (localEntrSttsChngSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngSeqno"));

				elementList.add(localEntrSttsChngSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngSeqno));
			}
			if (localRspEntrSttsChngSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "rspEntrSttsChngSeqno"));

				elementList.add(localRspEntrSttsChngSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRspEntrSttsChngSeqno));
			}
			if (localEntrSttsValdStrtDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsValdStrtDt"));

				elementList.add(localEntrSttsValdStrtDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsValdStrtDt));
			}
			if (localEntrSttsValdEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsValdEndDt"));

				elementList.add(localEntrSttsValdEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsValdEndDt));
			}
			if (localEntrSttsChngRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngRsnCd"));

				elementList.add(localEntrSttsChngRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngRsnCd));
			}
			if (localEntrSttsChngRsnDtlCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngRsnDtlCd"));

				elementList.add(localEntrSttsChngRsnDtlCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngRsnDtlCd));
			}
			if (localRspRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "rspRsnCd"));

				elementList.add(localRspRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRspRsnCd));
			}
			if (localEntrSttsChngRsnDtlNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngRsnDtlNm"));

				elementList.add(localEntrSttsChngRsnDtlNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngRsnDtlNm));
			}
			if (localMemoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "memo"));

				elementList.add(localMemo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMemo));
			}
			if (localItemIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "itemId"));

				elementList.add(localItemId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localItemId));
			}
			if (localManfSerialNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "manfSerialNo"));

				elementList.add(localManfSerialNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localManfSerialNo));
			}
			if (localProdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "prodNm"));

				elementList.add(localProdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNm));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static DsUseSvcListOutVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsUseSvcListOutVO object = new DsUseSvcListOutVO();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"DsUseSvcListOutVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsUseSvcListOutVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "prodNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "aceno")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAceno(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "prodCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "billAcntNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "hldrCustNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setHldrCustNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "rlusrCustNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "svcgrpCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcgrpCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "svcKdCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcKdCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "svcCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "vtNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setVtNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "ltpymSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtpymSttsCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "frstEntrDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFrstEntrDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cablPcCont")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCablPcCont(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "stopCablPcCont")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setStopCablPcCont(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "wrlsPcCont")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setWrlsPcCont(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "stopWrlsPcCont")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setStopWrlsPcCont(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cnvgKdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCnvgKdCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cnvgKdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCnvgKdNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "cnvgNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCnvgNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sendPhbYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSendPhbYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "icallPhbYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIcallPhbYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "sbgnDlrCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSbgnDlrCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "rspEntrSttsChngSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRspEntrSttsChngSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsValdStrtDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsValdStrtDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsValdEndDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsValdEndDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngRsnCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngRsnDtlCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngRsnDtlCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "rspRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRspRsnCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "entrSttsChngRsnDtlNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngRsnDtlNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "memo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setMemo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "itemId")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setItemId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "manfSerialNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setManfSerialNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sb638", "prodNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	private org.apache.axiom.om.OMElement toOM(
			lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcList param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcList.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcList param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcList.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param, java.lang.Class type,
			java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {

		try {

			if (lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcList.class.equals(type)) {

				return lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcList.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse.class
					.equals(type)) {

				return lguplus.u3.webservice.sb638.RetrieveUseSvcListServiceStub.RetrieveUseSvcListResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
