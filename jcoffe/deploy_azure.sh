
echo "Compiling project...."

mvn clean package -am -DskipTests=true

echo "Uploading project..."

scp -i apiv2_key.pem target/*.jar* azureuser@jcoffeeapi.northeurope.cloudapp.azure.com:/home/azureuser/app/backend
scp -i apiv2_key.pem docker-compose.yml azureuser@jcoffeeapi.northeurope.cloudapp.azure.com:/home/azureuser/app/backend

echo "Deploying in Docker of Azure Machine..."
ssh -i apiv2_key.pem azureuser@jcoffeeapi.northeurope.cloudapp.azure.com 'cd /home/azureuser/app/backend && sudo docker compose -f docker-compose.yml down && sudo docker compose -f docker-compose.yml up -d'

#ssh -i apiv2_key.pem azureuser@jcoffeeapi.northeurope.cloudapp.azure.com