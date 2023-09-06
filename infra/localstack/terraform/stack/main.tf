resource "aws_dynamodb_table" "my_dynamodb_table_in_localstack" {
  name           = "my_dynamodb_table_in_localstack"
  read_capacity  = 20
  write_capacity = 20
  hash_key       = "ID"

  attribute {
    name = "ID"
    type = "N"
  }
}