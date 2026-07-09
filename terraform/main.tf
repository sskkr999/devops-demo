# Security group for the tooling server
resource "aws_security_group" "tooling_sg" {
  name        = "tooling-server-sg"
  description = "Security group for SonarQube and monitoring tools"

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "SonarQube"
    from_port   = 9000
    to_port     = 9000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Grafana"
    from_port   = 3000
    to_port     = 3000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Prometheus"
    from_port   = 9090
    to_port     = 9090
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "tooling-server-sg"
  }
}

# The EC2 instance itself
resource "aws_instance" "tooling_server" {
  ami                    = "ami-0a02a779008fa3b99" # Ubuntu 24.04 LTS, us-east-1 - verify this is current
  instance_type          = "t3.small"
  key_name               = "devopsdemo"
  vpc_security_group_ids = [aws_security_group.tooling_sg.id]

  root_block_device {
    volume_size = 30
  }

  tags = {
    Name = "devops-tooling-server"
  }
}

# Output the public IP once created
output "tooling_server_public_ip" {
  value = aws_instance.tooling_server.public_ip
}