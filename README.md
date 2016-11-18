Setting Up New Google Cloud Project

1. Open Google Cloud SDK Shell. From the gcloud install directory, enter:

        gcloud init

2. Create a new configuration.

3. Choose/create the correct gmail account associated with the project.

4.  Associate the workspace project to the google cloud project.

        gcloud config set project 'Project Id'

5. Build with maven.

        mvn clean package

6. Deploy to google cloud.

        mvn gcloud:deploy

