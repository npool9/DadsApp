package com.nathanpool.updatepanel;

import java.util.concurrent.ExecutionException;

/*
 * The Main Activity of the app. This invokes the worker methods for getting information from the
 * YouTube (and later on, maybe some others) API. Given that information, we display the queried
 * videos on a scrollable page.
 */
public class Controller {
    // Miami of Ohio, Miami Redhawks, Elon University, Elon Phoenix, NC State, NC State Athletics,
    // NC State Computer Science, SAS Software
    static final String[] CHANNELS = {"UCAgUwIZC4DJrfW63oyHS0ag", "UC6O0l1HztB7XaXu_U1SL1sw",
            "UCxrGKkHJlwnQc-jEi7kt3nw", "UCFPy3Gk33MmE29opfDhJSvw",
            "UCokzuZa68EyFqg5OPUnreLA", "UCQWFfk0MydN-rtOI_t_iTHw", "UCSIAvdy4KEeWaFa8RbhY0sg",
            "UCtOiaxdcY_6RsRUpBg_2LoQ"};
    static final int LEN = CHANNELS.length;

    protected String[] control() {
        ResponseExtraction re = new ResponseExtraction();
        String[] responses = new String[LEN];

        // This gets the json responses to a specific http request that is denoted as two
        // partitioned constants in the ResponseExtraction class. This is done for LEN
        // different channels, again, denoted as constants.
        for (int i = 0; i < LEN; i++) {
            try {
                responses[i] = re.getResponse(i);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Get the actual links from the json information that was requested from the API
        // We will use these to display the videos relevant to that point in time.
        // Recall: these links will be updated as each channel uploads new videos
        String[] ids = re.extractIDs(responses);

        return(ids);
    }

}
