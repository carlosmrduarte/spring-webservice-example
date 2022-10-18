FROM maven:3.8.3-openjdk-17-slim@sha256:950889e13c6a50fa7426d67369d3afbc59608981883e22133cd385fc209c831b

RUN apt-get update && \
    apt-get install make -y && \
    apt-get install git -y

# Install Kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl && \
    echo 'alias k="kubectl"' >> ~/.bashrc

# Install Helm CLI
RUN curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 && \
    chmod 700 get_helm.sh && \
    ./get_helm.sh && \
    rm ./get_helm.sh
