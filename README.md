# testautomation-confluencePublisher
confluencePublisher
This Project demonstrates the ability to establish a connection to Confluence / Collaboration or any Web board and publish Test Automation results.

This usage of this project is just not limited to Test Automation usage, it could be used for broader scope of having this integrated with any tool and having automatic updates published.

Below is Maven goal

clean install compile assembly:single exec:java -Dexec.mainClass="com.confluence.publish.PublishAggregatedData"
