package com.gettingmobile.google.reader.rest;

import com.gettingmobile.google.Authenticator;
import com.gettingmobile.rest.ContentIOException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.List;

public class GetItemsByReferenceRequest extends FormDataRequest<ItemStream> {
    private static final ItemStreamExtractor itemStreamExtractor = new ItemStreamExtractor();
	private final String[] itemReferences;

	public GetItemsByReferenceRequest(Authenticator authenticator, String[] strings) throws URISyntaxException {
		super("stream/items/contents", authenticator);
		this.itemReferences = strings;
	}

	@Override
	protected void setFormValues(List<NameValuePair> formValues) {
		super.setFormValues(formValues);

        for (String ref : itemReferences) {
            formValues.add(new BasicNameValuePair("i", ref));
        }
	}

    @Override
    public ItemStream processResponse(HttpResponse response) throws ContentIOException {
        return itemStreamExtractor.extract(response.getEntity());
    }
}
